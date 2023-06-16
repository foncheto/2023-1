import random
import requests
import json

characters = [
  "Theodore Twombly",
  "Samantha",
  "Amy",
  "Catherine",
  "Charles",
  "Amy's Husband",
  "Paul",
  "Tatiana",
  "Meena",
  "Cindy",
]

places = [
  "Theodore's Apartment",
  "The Coffee Shop",
  "The Museum",
  "The Library",
  "The Park",
  "The Beach",
  "The Opera House",
  "The Airport",
  "Shanghai",
  "Los Angeles",
]

themes = [
  "Loneliness and the human need for connection",
  "Love in the digital age",
  "The consequences of artificial intelligence",
  "The nature of consciousness",
  "The meaning of love",
  "The future of humanity",
]

actors = [
  "Joaquin Phoenix",
  "Scarlett Johansson",
  "Amy Adams",
  "Rooney Mara",
  "Olivia Wilde",
  "Matt Letscher",
  "Luka Jones",
  "Chris Pratt",
  "Kristen Wiig",
  "Bill Hader",
  "Portia Doubleday",
  "Soko",
  "Brian Cox",
  "Spike Jonze",
  "Steve Zissis",
  "Alia Janine"
]

def generate_random_combination():
  numbers = [str(i) for i in range(10)]
  letters = [chr(i) for i in range(ord('a'), ord('z') + 1)]

  combination = ""
  for i in range(8):
    combination += random.choice(numbers)

  for i in range(3):
    combination += random.choice(letters)

  return combination

def postdata(url,data):
    response = requests.post(url, data=data)
    print(response.text)

url = "https://kwpqrqfgiqpkhqj.form.io/control4her/submission"

for i in range(10000):
    data = {
        "data": {
            "character": random.choice(characters),
            "place": random.choice(places),
            "theme": random.choice(themes),
            "actor": random.choice(actors),
            "combination": generate_random_combination()
        }
    }
    json_data = json.dumps(data)
    postdata(url,json_data)
