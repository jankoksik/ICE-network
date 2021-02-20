import socket
import json

HOST = '127.0.0.1'
PORT = 65530


class Game():
    numbers = 9
    List = []
    steps = 0

    def __init__(self, nmbr):
        self.numbers = nmbr
        for x in range (nmbr):
            self.List.append(0)

    def Reset(self):
        for x in range(self.numbers):
            self.List[x]=0
            self.steps=0

    def Color(self, place):
        self.steps += 1
        self.List[place] = 1

    def GetList(self):
        return self.List

    def GetSteps(self):
        return self.steps

def PackData(a):
    data = {}
    data['pkg'] = []
    data['pkg'].append({
        'response' : a
    })
    return json.dumps(data)



s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST,PORT))
s.listen(10)
g = Game(9)
while True:
    conn, addr = s.accept()
    from_client = ''
    data = conn.recv(4096)
    if data:
        data = json.loads(data)

        response = ""
        for p in data['pkg']:
            print("client action :   " + p["action"])
            if(p['action'] == "Put"):
                g.Color(int(p["number"]))
                response = PackData("done")
            elif (p['action'] == "Reset"):
                g.Reset()
                response = PackData("done")
            elif (p['action'] == "GetState"):
                response = PackData(g.GetList())
                print(response)
            elif (p['action'] == "GetSteps"):
                response = PackData(g.GetSteps())


    conn.send(response.encode('utf-8'))
    conn.close()
    print ('client disconnected')





