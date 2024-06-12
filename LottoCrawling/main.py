import requests
from bs4 import BeautifulSoup
import pandas as pd

# API URL
url = "https://dhlottery.co.kr/gameResult.do?method=allWinExel&gubun=byWin&nowPage=&drwNoStart=1&drwNoEnd=1123"

def fetch_and_parse_lottery_data(url):
    # Fetch the data from the API
    response = requests.get(url)
    if response.status_code != 200:
        print("Failed to fetch data")
        return

    # Use BeautifulSoup to parse HTML data
    soup = BeautifulSoup(response.content, 'html.parser')

    # Initialize a list to store each row of lottery data
    lottery_data = []

    # Find all table rows with data
    rows = soup.find_all('tr')
    for row in rows[3:]:  # Assuming the first two rows are headers
        cols = row.find_all('td')
        if len(cols) == 19:  # Ensure the row has all the necessary columns
            numbers = [num.text.strip() for num in cols[12:18]]  # Numbers 1 to 6
            data = [
                int(cols[0].text.strip()),  # Draw number
                *numbers,  # Expanding the list of winning numbers
                int(cols[3].text.strip().replace('원', '').replace(',', '')),  # 1st prize
                int(cols[5].text.strip().replace('원', '').replace(',', '')),  # 2nd prize
                int(cols[7].text.strip().replace('원', '').replace(',', '')),  # 3rd prize
                int(cols[9].text.strip().replace('원', '').replace(',', '')),  # 4th prize
                int(cols[11].text.strip().replace('원', '').replace(',', ''))  # 5th prize
            ]
            lottery_data.append(data)

    return lottery_data

# Fetch and organize the lottery data
lottery_data = fetch_and_parse_lottery_data(url)
if lottery_data:
    # Create a DataFrame for better visualization
    columns = ['draw_number', 'num1', 'num2', 'num3', 'num4', 'num5', 'num6', '1st_prize', '2nd_prize', '3rd_prize', '4th_prize', '5th_prize']
    df = pd.DataFrame(lottery_data, columns=columns)

    # Sort DataFrame by 'draw_number' in ascending order
    df.sort_values('draw_number', ascending=True, inplace=True)

    # Save the DataFrame to a CSV file without headers
    df.to_csv('lotto.csv', index=False, header=False)

    print("Data saved to lotto.csv sorted by draw number without headers")
else:
    print("No data to save")
