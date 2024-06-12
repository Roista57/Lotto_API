import torch
import numpy as np
import os

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

class LottoModel(torch.nn.Module):
    def __init__(self):
        super(LottoModel, self).__init__()
        self.lstm = torch.nn.LSTM(45, 128, batch_first=True)
        self.fc = torch.nn.Linear(128, 45)

    def forward(self, x):
        _, (h_n, _) = self.lstm(x)
        x = self.fc(h_n.squeeze(0))
        return torch.sigmoid(x)

model = LottoModel().to(device)
model_path = r'F:\ToyProject\Lotto API\LottoCrawling\lotto_model.pth'

if os.path.exists(model_path):
    model.load_state_dict(torch.load(model_path, map_location=device))
    model.to(device)

# Function to convert one-hot encoding to numbers
def ohbin2numbers(ohbin, k=6):
    # 확률 값에 따라 상위 k개 인덱스를 찾습니다.
    top_indices = np.argsort(ohbin)[-k:]
    # 인덱스를 로또 번호로 변환합니다. (1을 더합니다)
    return [index + 1 for index in sorted(top_indices)]

def numbers_to_ohbin(numbers):
    ohbin = np.zeros(45)
    for i in range(6):
        ohbin[int(numbers[i]) - 1] = 1
    return ohbin

rows = np.loadtxt(r"F:\ToyProject\Lotto API\LottoCrawling\lotto.csv", delimiter=",")
numbers = rows[:, 1:7]
ohbins = np.array([numbers_to_ohbin(numbers_row) for numbers_row in numbers])

x_samples = torch.tensor(ohbins, dtype=torch.float32)  # 이제 x_samples 정의가 포함됨

# 모델 평가 및 예측
# model.eval()
# with torch.no_grad():
#     for i in range(5):  # 다양한 샘플에 대한 예측을 시도합니다.
#         sample_idx = np.random.randint(len(x_samples))  # 무작위 인덱스 선택
#         last_draw = x_samples[sample_idx].to(device)
#         prediction = model(last_draw.unsqueeze(0))
#         prediction_np = prediction.cpu().numpy().squeeze()
#         predicted_numbers = ohbin2numbers(prediction_np)
#         print(predicted_numbers)

# 모델 평가 및 예측
model.eval()
with torch.no_grad():
    sample_idx = np.random.randint(len(x_samples))  # 무작위 인덱스 선택
    last_draw = x_samples[sample_idx].to(device)
    prediction = model(last_draw.unsqueeze(0))
    prediction_np = prediction.cpu().numpy().squeeze()
    predicted_numbers = ohbin2numbers(prediction_np)
    print(predicted_numbers)
