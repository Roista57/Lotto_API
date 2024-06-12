# https://github.com/youtube-jocoding/lotto-deeplearning

import requests
from bs4 import BeautifulSoup
import pandas as pd
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import numpy as np
import os

# API URL
url = "https://dhlottery.co.kr/gameResult.do?method=allWinExel&gubun=byWin&nowPage=&drwNoStart=1&drwNoEnd=1123"


def fetch_and_parse_lottery_data(url):
    response = requests.get(url)
    if response.status_code != 200:
        print("Failed to fetch data")
        return

    soup = BeautifulSoup(response.content, 'html.parser')
    lottery_data = []
    rows = soup.find_all('tr')
    for row in rows[3:]:
        cols = row.find_all('td')
        if len(cols) == 19:
            numbers = [num.text.strip() for num in cols[12:18]]
            data = [
                int(cols[0].text.strip()),
                *numbers,
                int(cols[3].text.strip().replace('원', '').replace(',', '')),
                int(cols[5].text.strip().replace('원', '').replace(',', '')),
                int(cols[7].text.strip().replace('원', '').replace(',', '')),
                int(cols[9].text.strip().replace('원', '').replace(',', '')),
                int(cols[11].text.strip().replace('원', '').replace(',', ''))
            ]
            lottery_data.append(data)

    return lottery_data


lottery_data = fetch_and_parse_lottery_data(url)
if lottery_data:
    columns = ['draw_number', 'num1', 'num2', 'num3', 'num4', 'num5', 'num6', '1st_prize', '2nd_prize', '3rd_prize',
               '4th_prize', '5th_prize']
    df = pd.DataFrame(lottery_data, columns=columns)
    df.sort_values('draw_number', ascending=True, inplace=True)
    df.to_csv('lotto.csv', index=False, header=False)
    print("Data saved to lotto.csv sorted by draw number without headers")
else:
    print("No data to save")


# Function to convert numbers to one-hot encoding
def numbers2ohbin(numbers):
    ohbin = np.zeros(45)
    for i in range(6):
        ohbin[int(numbers[i]) - 1] = 1
    return ohbin


rows = np.loadtxt("./lotto.csv", delimiter=",")
numbers = rows[:, 1:7]
ohbins = np.array(list(map(numbers2ohbin, numbers)))
x_samples = torch.tensor(ohbins[:-1], dtype=torch.float32)
y_samples = torch.tensor(ohbins[1:], dtype=torch.float32)
dataset = TensorDataset(x_samples, y_samples)
train_loader = DataLoader(dataset, batch_size=1, shuffle=True)
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")


class LottoModel(nn.Module):
    def __init__(self):
        super(LottoModel, self).__init__()
        self.lstm = nn.LSTM(45, 128, batch_first=True)
        self.fc = nn.Linear(128, 45)

    def forward(self, x):
        _, (h_n, _) = self.lstm(x)
        x = self.fc(h_n.squeeze(0))
        return torch.sigmoid(x)


model = LottoModel().to(device)
criterion = nn.BCELoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)


def train_model(num_epochs):
    model.train()
    for epoch in range(num_epochs):
        for data in train_loader:
            inputs, labels = data[0].to(device), data[1].to(device)
            optimizer.zero_grad()
            outputs = model(inputs.unsqueeze(0))
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()
        print(f'Epoch {epoch + 1} completed.')


model_path = r'F:\ToyProject\Lotto API\LottoCrawling\lotto_model.pth'
train_model(100)
torch.save(model.state_dict(), model_path)
print("Model saved to", model_path)
