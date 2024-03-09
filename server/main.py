from flask import Flask, jsonify, send_from_directory, request
app=Flask(__name__)

places = [
    {
        'id': 1,
        'loc': 'Hội An',
        'province': 'Quảng Nam',
        'part': 'mid',
        'content': '# Trước thế kỷ II\nKết quả nhiều cuộc thăm dò, quan sát các di tích mộ táng: Bãi Ông…',
        'imgUrls': [
            "asset/image/hoi_an.png",
            "asset/image/hoi_an_2.png"
        ]
    },
    {
        'id': 2,
        'loc': 'Hồ Gươm',
        'province': 'Hà Nội',
        'part': 'Bắc',
        'content': 'Nội dung về Hồ Gươm',
        'imgUrls': [
            "asset/image/ho.png",
            "asset/image/ho_2.png"
        ]
    },
    {
        'id': 3,
        'loc': 'Phong nha- kẻ bàng',
        'province': 'Quảng Bình',
        'part': 'Trung',
        'content': 'Nội dung về Phong nha- kẻ bàng',
        'imgUrls': [
            "asset/image/dong.png",
            "asset/image/dong_2.png"
        ]
    },
    {
        'id': 4,
        'loc': 'Chợ Bến Thành',
        'province': 'Quảng Nam',
        'part': 'Nam',
        'content': 'Nội dung về Chợ Bến Thành',
        'imgUrls': [
            "asset/image/cho.png",
            "asset/image/cho_2.png"
        ]
    },
    {
        'id': 5,
        'loc': 'Mũi Cà Mau',
        'province': 'Cà Mau',
        'part': 'Nam',
        'content': 'Nội dung về Mũi Cà Mau',
        'imgUrls': [
            "asset/image/mui_ca_mau.png",
            "asset/image/mui_ca_mau_2.png"
        ]
    }
]



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
                    'thumbUrl': 'asset/thumb/hoi_an.png'
                },
                {
                    'id': 2,
                    'loc': 'Hồ Gươm',
                    'province': 'Hà Nội',
                    'part': 'Bắc',
                    'summary': 'Hanoi :)',
                    'thumbUrl': 'asset/thumb/ho.png'
                },
                {
                    'id': 3,
                    'loc': 'Phong nha- kẻ bàng',
                    'province': 'Quảng Bình',
                    'part': 'Trung',
                    'summary': 'Động đẹp',
                    'thumbUrl': 'asset/thumb/dong.png'
                },
                {
                    'id': 4,
                    'loc': 'Chợ Bến Thành',
                    'province': 'Quảng Nam',
                    'part': 'Nam',
                    'summary': 'Chợ đẹp',
                    'thumbUrl': 'asset/thumb/cho.png'
                },
                {
                    'id': 5,
                    'loc': 'Mũi Cà Mau',
                    'province': 'Cà Mau',
                    'part': 'Nam',
                    'summary': 'Nó đẹp',
                    'thumbUrl': 'asset/thumb/mui_ca_mau.png'
                }
                ],
                'result': 'success'
            }
    )


@app.route('/place/getdetail', methods=['GET'])
def get_place_detail_by_id():
    place_id = request.args.get('id')
    if place_id is not None:
        try:
            place_id = int(place_id)
            place = next((p for p in places if p['id'] == place_id), None)
            if place:
                # Tạo JSON với cấu trúc tương tự như đoạn bạn đã cung cấp
                response = {
                    "loc": place["loc"],
                    "province": place["province"],
                    "part": place["part"],
                    "content": place["content"],
                    "imgUrls": place["imgUrls"],
                    "result": "success"
                }
                return jsonify(response)
            else:
                return jsonify({'message': 'Place not found', 'result': 'failure'}), 404
        except ValueError:
            return jsonify({'message': 'Invalid place id', 'result': 'failure'}), 400
    else:
        return jsonify({'message': 'Place id is missing', 'result': 'failure'}), 400

if __name__=='__main__':
    app.run(host='0.0.0.0',port=8080)
