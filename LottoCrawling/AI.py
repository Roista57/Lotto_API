import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import numpy as np
import matplotlib.pyplot as plt
import os


# Function to convert numbers to one-hot encoding
def numbers2ohbin(numbers):
    ohbin = np.zeros(45)
    for i in range(6):
        ohbin[int(numbers[i]) - 1] = 1
    return ohbin


# Function to convert one-hot encoding to numbers
def ohbin2numbers(ohbin, threshold=0.5):
    return [i + 1 for i, val in enumerate(ohbin) if val >= threshold]


# Load and prepare data
rows = np.loadtxt("./lotto.csv", delimiter=",")
numbers = rows[:, 1:7]
ohbins = np.array(list(map(numbers2ohbin, numbers)))
x_samples = torch.tensor(ohbins[:-1], dtype=torch.float32)
y_samples = torch.tensor(ohbins[1:], dtype=torch.float32)

# Dataset and DataLoader
dataset = TensorDataset(x_samples, y_samples)
train_loader = DataLoader(dataset, batch_size=1, shuffle=True)

# Check for CUDA
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")


# Define the model
class LottoModel(nn.Module):
    def __init__(self):
        super(LottoModel, self).__init__()
        self.lstm = nn.LSTM(45, 128, batch_first=True)
        self.fc = nn.Linear(128, 45)

    def forward(self, x):
        _, (h_n, _) = self.lstm(x)
        x = self.fc(h_n.squeeze(0))
        return torch.sigmoid(x)


# Initialize model, loss, and optimizer
model = LottoModel().to(device)
criterion = nn.BCELoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)


# Train the model
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


# Load or train model
model_path = 'lotto_model.pth'
if os.path.exists(model_path):
    model.load_state_dict(torch.load(model_path))
    model.to(device)
    print("Model loaded from", model_path)
else:
    train_model(100)
    torch.save(model.state_dict(), model_path)
    print("Model saved to", model_path)

# Predict the next draw
model.eval()
with torch.no_grad():
    last_draw = x_samples[-1].to(device)
    prediction = model(last_draw.unsqueeze(0))
    predicted_numbers = ohbin2numbers(prediction.cpu().numpy().squeeze(),
                                      threshold=0.2)  # Adjust the threshold as needed
    print('Predicted numbers:', predicted_numbers)

# Optional: Plot training process or more diagnostics
