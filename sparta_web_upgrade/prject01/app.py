from flask import Flask, render_template, jsonify, request
app = Flask(__name__)

from pymongo import MongoClient
# client = MongoClient('localhost', 27017)
client = MongoClient('mongodb://test:test@localhost', 27017)
db = client.dbsparta_upgrade

from datetime import datetime

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/diary', methods=['GET'])
def show_diary():
    diaries = list(db.diary.find({},{'_id':False}))

    return jsonify({'all_diary': diaries})

@app.route('/diary', methods=['POST'])
def save_diary():
    title_receive = request.form['title_give']
    content_receive = request.form['content_give']

    # 서버쪽에서 파일 받기
    file = request.files["file_give"]

    # extension은 jpg를 가져 올 수 있다
    extension = file.filename.split('.')[-1]
    today = datetime.now()
    mytime = today.strftime('%Y-%m-%d-%H-%M-%S')

    filename = f'file-{mytime}'

    # save_to는 파일저장한 것을 경로에 두겠다 static/ 에 두겠다는 의미
    save_to = f'static/{filename}.{extension}'
    # file.save(경로)
    file.save(save_to)


    doc = {'title': title_receive
        , 'content': content_receive
        , 'file' :  f'{filename}.{extension}'
        , 'time' : mytime
           }
    db.diary.insert_one(doc)

    return jsonify({'msg': '저장완료'})


if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)