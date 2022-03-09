import requests
from bs4 import BeautifulSoup
from .get_id_info import getIDinfo

def get_more_info():
    URL = getIDinfo("방황한대학생")
    response = requests.get(URL)

    if response.status_code == 200:
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
         = soup.select()
    else : 
        return response.status_code