import urllib.request
from bs4 import BeautifulSoup
import requests

def getIDinfo(MapleName):
    base_URL = 'https://maplestory.nexon.com/Ranking/World/Total'
    search_URL = '/Common/Character/Detail/'
    MapleName = urllib.parse.quote(MapleName)
    path = '?c=' + MapleName + '&w=0'
    response = requests.get(base_URL + path)

    if response.status_code == 200:
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
        soup = soup.select('.search_com_chk > .left > dl > dt > a')
        for t in soup:
            link = t['href']
        link = link[len(search_URL + MapleName):]
        return 'https://maplestory.nexon.com' + search_URL + MapleName + '/' + 'Equipment' + link
    else : 
        return response.status_code

print(getIDinfo("방황한대학생"))