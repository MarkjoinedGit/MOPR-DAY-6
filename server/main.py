from flask import Flask, jsonify, send_from_directory
app=Flask(__name__)

@app.route('/asset/<path:path>')
def send_asset(path):
    return send_from_directory('asset', path)

@app.route('/place',methods=['GET'])
def getPlace():
    return jsonify(
            {
                'places': [
                {
                    'id': 1,
                    'loc': 'Hội An',
                    'province': 'Quảng Nam',
                    'part': 'Trung',
                    'summary': 'Phố cổ Hội An từng là một thương cảng quốc tế sầm uất, gồm những di sản kiếntrúc đã có từ hàng trăm nămtrước, được UNESCO công nhậnlà di sản văn hóa thế giới từ năm1999.',
                    'thumbUrl': 'asset/hoi_an.png'
                },
                {
                    'id': 2,
                    'loc': 'Hồ Gươm',
                    'province': 'Hà Nội',
                    'part': 'Bắc',
                    'summary': 'Hanoi :)',
                    'thumbUrl': 'asset/ho.png'
                },
                {
                    'id': 3,
                    'loc': 'Phong nha- kẻ bàng',
                    'province': 'Quảng Bình',
                    'part': 'Trung',
                    'summary': 'Động đẹp',
                    'thumbUrl': 'asset/dong.png'
                },
                {
                    'id': 4,
                    'loc': 'Chợ Bến Thành',
                    'province': 'Quảng Nam',
                    'part': 'Nam',
                    'summary': 'Chợ đẹp',
                    'thumbUrl': 'asset/cho.png'
                },
                {
                    'id': 5,
                    'loc': 'Mũi Cà Mau',
                    'province': 'Cà Mau',
                    'part': 'Nam',
                    'summary': 'Nó đẹp',
                    'thumbUrl': 'asset/mui_ca_mau.png'
                }
                ],
                'result': 'success'
            }
    )

if __name__=='__main__':
    app.run(host='0.0.0.0',port=8080)
