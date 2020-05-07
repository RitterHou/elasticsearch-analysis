## Qianmi Analyzer for Elasticsearch

在创建索引时自定义analyzer

    PUT http://localhost:9200/products
    
    {
        "settings": {
            "analysis": {
                "analyzer": {
                    "qm_standard": {
                        "type": "qm_standard"
                    },
                    "prefix": {
                        "type": "sub",
                        "section": "0:3;0:5"
                    },
                    "postfix": {
                        "type": "sub",
                        "section": "-6:-1;-4:-1"
                    }
                }
            }
        },
        "mappings": {
            "properties": {
                "title": {
                    "type": "text",
                    "analyzer": "qm_standard"
                }
            }
        }
    }

测试qm_standard分词器

    POST http://localhost:9200/products/_analyze
    
    {
        "analyzer": "qm_standard",
        "text": "のCIM(CROSS-IM2232323)11 一款面向开发者的 IM(即时通讯)系统；同时提供了一些组件帮助开发者构建一款属于自己可水平扩展的 IM 。借助 CIM 你可以实现以下需求：IM 即时通讯系统。适用于 APP 的消息推送中间件。IOT 海量连接场の景中的消息透传中间件。在使用或开发过程中有任何疑问都可联系我。11155a55哈哈😆😁111"
    }
    
    {
        "tokens": [
            {
                "token": "の",
                "start_offset": 0,
                "end_offset": 1,
                "type": "CJK",
                "position": 0
            },
            {
                "token": "CIM",
                "start_offset": 2,
                "end_offset": 5,
                "type": "word",
                "position": 1
            },
            {
                "token": "CROSS",
                "start_offset": 6,
                "end_offset": 11,
                "type": "word",
                "position": 2
            },
            {
                "token": "IM",
                "start_offset": 12,
                "end_offset": 14,
                "type": "word",
                "position": 3
            },
            {
                "token": "2232323",
                "start_offset": 14,
                "end_offset": 21,
                "type": "number",
                "position": 4
            },
            {
                "token": "11",
                "start_offset": 22,
                "end_offset": 24,
                "type": "number",
                "position": 5
            },
            {
                "token": "一",
                "start_offset": 24,
                "end_offset": 25,
                "type": "CJK",
                "position": 6
            },
            {
                "token": "款",
                "start_offset": 25,
                "end_offset": 26,
                "type": "CJK",
                "position": 7
            },
            {
                "token": "面",
                "start_offset": 26,
                "end_offset": 27,
                "type": "CJK",
                "position": 8
            },
            {
                "token": "向",
                "start_offset": 27,
                "end_offset": 28,
                "type": "CJK",
                "position": 9
            },
            {
                "token": "开",
                "start_offset": 28,
                "end_offset": 29,
                "type": "CJK",
                "position": 10
            },
            {
                "token": "发",
                "start_offset": 29,
                "end_offset": 30,
                "type": "CJK",
                "position": 11
            },
            {
                "token": "者",
                "start_offset": 30,
                "end_offset": 31,
                "type": "CJK",
                "position": 12
            },
            {
                "token": "的",
                "start_offset": 31,
                "end_offset": 32,
                "type": "CJK",
                "position": 13
            },
            {
                "token": "IM",
                "start_offset": 34,
                "end_offset": 36,
                "type": "word",
                "position": 14
            },
            {
                "token": "即",
                "start_offset": 36,
                "end_offset": 37,
                "type": "CJK",
                "position": 15
            },
            {
                "token": "时",
                "start_offset": 37,
                "end_offset": 38,
                "type": "CJK",
                "position": 16
            },
            {
                "token": "通",
                "start_offset": 38,
                "end_offset": 39,
                "type": "CJK",
                "position": 17
            },
            {
                "token": "讯",
                "start_offset": 39,
                "end_offset": 40,
                "type": "CJK",
                "position": 18
            },
            {
                "token": "系",
                "start_offset": 41,
                "end_offset": 42,
                "type": "CJK",
                "position": 19
            },
            {
                "token": "统",
                "start_offset": 42,
                "end_offset": 43,
                "type": "CJK",
                "position": 20
            },
            {
                "token": "同",
                "start_offset": 44,
                "end_offset": 45,
                "type": "CJK",
                "position": 21
            },
            {
                "token": "时",
                "start_offset": 45,
                "end_offset": 46,
                "type": "CJK",
                "position": 22
            },
            {
                "token": "提",
                "start_offset": 46,
                "end_offset": 47,
                "type": "CJK",
                "position": 23
            },
            {
                "token": "供",
                "start_offset": 47,
                "end_offset": 48,
                "type": "CJK",
                "position": 24
            },
            {
                "token": "了",
                "start_offset": 48,
                "end_offset": 49,
                "type": "CJK",
                "position": 25
            },
            {
                "token": "一",
                "start_offset": 49,
                "end_offset": 50,
                "type": "CJK",
                "position": 26
            },
            {
                "token": "些",
                "start_offset": 50,
                "end_offset": 51,
                "type": "CJK",
                "position": 27
            },
            {
                "token": "组",
                "start_offset": 51,
                "end_offset": 52,
                "type": "CJK",
                "position": 28
            },
            {
                "token": "件",
                "start_offset": 52,
                "end_offset": 53,
                "type": "CJK",
                "position": 29
            },
            {
                "token": "帮",
                "start_offset": 53,
                "end_offset": 54,
                "type": "CJK",
                "position": 30
            },
            {
                "token": "助",
                "start_offset": 54,
                "end_offset": 55,
                "type": "CJK",
                "position": 31
            },
            {
                "token": "开",
                "start_offset": 55,
                "end_offset": 56,
                "type": "CJK",
                "position": 32
            },
            {
                "token": "发",
                "start_offset": 56,
                "end_offset": 57,
                "type": "CJK",
                "position": 33
            },
            {
                "token": "者",
                "start_offset": 57,
                "end_offset": 58,
                "type": "CJK",
                "position": 34
            },
            {
                "token": "构",
                "start_offset": 58,
                "end_offset": 59,
                "type": "CJK",
                "position": 35
            },
            {
                "token": "建",
                "start_offset": 59,
                "end_offset": 60,
                "type": "CJK",
                "position": 36
            },
            {
                "token": "一",
                "start_offset": 60,
                "end_offset": 61,
                "type": "CJK",
                "position": 37
            },
            {
                "token": "款",
                "start_offset": 61,
                "end_offset": 62,
                "type": "CJK",
                "position": 38
            },
            {
                "token": "属",
                "start_offset": 62,
                "end_offset": 63,
                "type": "CJK",
                "position": 39
            },
            {
                "token": "于",
                "start_offset": 63,
                "end_offset": 64,
                "type": "CJK",
                "position": 40
            },
            {
                "token": "自",
                "start_offset": 64,
                "end_offset": 65,
                "type": "CJK",
                "position": 41
            },
            {
                "token": "己",
                "start_offset": 65,
                "end_offset": 66,
                "type": "CJK",
                "position": 42
            },
            {
                "token": "可",
                "start_offset": 66,
                "end_offset": 67,
                "type": "CJK",
                "position": 43
            },
            {
                "token": "水",
                "start_offset": 67,
                "end_offset": 68,
                "type": "CJK",
                "position": 44
            },
            {
                "token": "平",
                "start_offset": 68,
                "end_offset": 69,
                "type": "CJK",
                "position": 45
            },
            {
                "token": "扩",
                "start_offset": 69,
                "end_offset": 70,
                "type": "CJK",
                "position": 46
            },
            {
                "token": "展",
                "start_offset": 70,
                "end_offset": 71,
                "type": "CJK",
                "position": 47
            },
            {
                "token": "的",
                "start_offset": 71,
                "end_offset": 72,
                "type": "CJK",
                "position": 48
            },
            {
                "token": "IM",
                "start_offset": 74,
                "end_offset": 76,
                "type": "word",
                "position": 49
            },
            {
                "token": "借",
                "start_offset": 77,
                "end_offset": 78,
                "type": "CJK",
                "position": 50
            },
            {
                "token": "助",
                "start_offset": 78,
                "end_offset": 79,
                "type": "CJK",
                "position": 51
            },
            {
                "token": "CIM",
                "start_offset": 81,
                "end_offset": 84,
                "type": "word",
                "position": 52
            },
            {
                "token": "你",
                "start_offset": 84,
                "end_offset": 85,
                "type": "CJK",
                "position": 53
            },
            {
                "token": "可",
                "start_offset": 85,
                "end_offset": 86,
                "type": "CJK",
                "position": 54
            },
            {
                "token": "以",
                "start_offset": 86,
                "end_offset": 87,
                "type": "CJK",
                "position": 55
            },
            {
                "token": "实",
                "start_offset": 87,
                "end_offset": 88,
                "type": "CJK",
                "position": 56
            },
            {
                "token": "现",
                "start_offset": 88,
                "end_offset": 89,
                "type": "CJK",
                "position": 57
            },
            {
                "token": "以",
                "start_offset": 89,
                "end_offset": 90,
                "type": "CJK",
                "position": 58
            },
            {
                "token": "下",
                "start_offset": 90,
                "end_offset": 91,
                "type": "CJK",
                "position": 59
            },
            {
                "token": "需",
                "start_offset": 91,
                "end_offset": 92,
                "type": "CJK",
                "position": 60
            },
            {
                "token": "求",
                "start_offset": 92,
                "end_offset": 93,
                "type": "CJK",
                "position": 61
            },
            {
                "token": "IM",
                "start_offset": 95,
                "end_offset": 97,
                "type": "word",
                "position": 62
            },
            {
                "token": "即",
                "start_offset": 97,
                "end_offset": 98,
                "type": "CJK",
                "position": 63
            },
            {
                "token": "时",
                "start_offset": 98,
                "end_offset": 99,
                "type": "CJK",
                "position": 64
            },
            {
                "token": "通",
                "start_offset": 99,
                "end_offset": 100,
                "type": "CJK",
                "position": 65
            },
            {
                "token": "讯",
                "start_offset": 100,
                "end_offset": 101,
                "type": "CJK",
                "position": 66
            },
            {
                "token": "系",
                "start_offset": 101,
                "end_offset": 102,
                "type": "CJK",
                "position": 67
            },
            {
                "token": "统",
                "start_offset": 102,
                "end_offset": 103,
                "type": "CJK",
                "position": 68
            },
            {
                "token": "适",
                "start_offset": 104,
                "end_offset": 105,
                "type": "CJK",
                "position": 69
            },
            {
                "token": "用",
                "start_offset": 105,
                "end_offset": 106,
                "type": "CJK",
                "position": 70
            },
            {
                "token": "于",
                "start_offset": 106,
                "end_offset": 107,
                "type": "CJK",
                "position": 71
            },
            {
                "token": "APP",
                "start_offset": 109,
                "end_offset": 112,
                "type": "word",
                "position": 72
            },
            {
                "token": "的",
                "start_offset": 112,
                "end_offset": 113,
                "type": "CJK",
                "position": 73
            },
            {
                "token": "消",
                "start_offset": 113,
                "end_offset": 114,
                "type": "CJK",
                "position": 74
            },
            {
                "token": "息",
                "start_offset": 114,
                "end_offset": 115,
                "type": "CJK",
                "position": 75
            },
            {
                "token": "推",
                "start_offset": 115,
                "end_offset": 116,
                "type": "CJK",
                "position": 76
            },
            {
                "token": "送",
                "start_offset": 116,
                "end_offset": 117,
                "type": "CJK",
                "position": 77
            },
            {
                "token": "中",
                "start_offset": 117,
                "end_offset": 118,
                "type": "CJK",
                "position": 78
            },
            {
                "token": "间",
                "start_offset": 118,
                "end_offset": 119,
                "type": "CJK",
                "position": 79
            },
            {
                "token": "件",
                "start_offset": 119,
                "end_offset": 120,
                "type": "CJK",
                "position": 80
            },
            {
                "token": "IOT",
                "start_offset": 122,
                "end_offset": 125,
                "type": "word",
                "position": 81
            },
            {
                "token": "海",
                "start_offset": 125,
                "end_offset": 126,
                "type": "CJK",
                "position": 82
            },
            {
                "token": "量",
                "start_offset": 126,
                "end_offset": 127,
                "type": "CJK",
                "position": 83
            },
            {
                "token": "连",
                "start_offset": 127,
                "end_offset": 128,
                "type": "CJK",
                "position": 84
            },
            {
                "token": "接",
                "start_offset": 128,
                "end_offset": 129,
                "type": "CJK",
                "position": 85
            },
            {
                "token": "场",
                "start_offset": 129,
                "end_offset": 130,
                "type": "CJK",
                "position": 86
            },
            {
                "token": "の",
                "start_offset": 130,
                "end_offset": 131,
                "type": "CJK",
                "position": 87
            },
            {
                "token": "景",
                "start_offset": 131,
                "end_offset": 132,
                "type": "CJK",
                "position": 88
            },
            {
                "token": "中",
                "start_offset": 132,
                "end_offset": 133,
                "type": "CJK",
                "position": 89
            },
            {
                "token": "的",
                "start_offset": 133,
                "end_offset": 134,
                "type": "CJK",
                "position": 90
            },
            {
                "token": "消",
                "start_offset": 134,
                "end_offset": 135,
                "type": "CJK",
                "position": 91
            },
            {
                "token": "息",
                "start_offset": 135,
                "end_offset": 136,
                "type": "CJK",
                "position": 92
            },
            {
                "token": "透",
                "start_offset": 136,
                "end_offset": 137,
                "type": "CJK",
                "position": 93
            },
            {
                "token": "传",
                "start_offset": 137,
                "end_offset": 138,
                "type": "CJK",
                "position": 94
            },
            {
                "token": "中",
                "start_offset": 138,
                "end_offset": 139,
                "type": "CJK",
                "position": 95
            },
            {
                "token": "间",
                "start_offset": 139,
                "end_offset": 140,
                "type": "CJK",
                "position": 96
            },
            {
                "token": "件",
                "start_offset": 140,
                "end_offset": 141,
                "type": "CJK",
                "position": 97
            },
            {
                "token": "在",
                "start_offset": 142,
                "end_offset": 143,
                "type": "CJK",
                "position": 98
            },
            {
                "token": "使",
                "start_offset": 143,
                "end_offset": 144,
                "type": "CJK",
                "position": 99
            },
            {
                "token": "用",
                "start_offset": 144,
                "end_offset": 145,
                "type": "CJK",
                "position": 100
            },
            {
                "token": "或",
                "start_offset": 145,
                "end_offset": 146,
                "type": "CJK",
                "position": 101
            },
            {
                "token": "开",
                "start_offset": 146,
                "end_offset": 147,
                "type": "CJK",
                "position": 102
            },
            {
                "token": "发",
                "start_offset": 147,
                "end_offset": 148,
                "type": "CJK",
                "position": 103
            },
            {
                "token": "过",
                "start_offset": 148,
                "end_offset": 149,
                "type": "CJK",
                "position": 104
            },
            {
                "token": "程",
                "start_offset": 149,
                "end_offset": 150,
                "type": "CJK",
                "position": 105
            },
            {
                "token": "中",
                "start_offset": 150,
                "end_offset": 151,
                "type": "CJK",
                "position": 106
            },
            {
                "token": "有",
                "start_offset": 151,
                "end_offset": 152,
                "type": "CJK",
                "position": 107
            },
            {
                "token": "任",
                "start_offset": 152,
                "end_offset": 153,
                "type": "CJK",
                "position": 108
            },
            {
                "token": "何",
                "start_offset": 153,
                "end_offset": 154,
                "type": "CJK",
                "position": 109
            },
            {
                "token": "疑",
                "start_offset": 154,
                "end_offset": 155,
                "type": "CJK",
                "position": 110
            },
            {
                "token": "问",
                "start_offset": 155,
                "end_offset": 156,
                "type": "CJK",
                "position": 111
            },
            {
                "token": "都",
                "start_offset": 156,
                "end_offset": 157,
                "type": "CJK",
                "position": 112
            },
            {
                "token": "可",
                "start_offset": 157,
                "end_offset": 158,
                "type": "CJK",
                "position": 113
            },
            {
                "token": "联",
                "start_offset": 158,
                "end_offset": 159,
                "type": "CJK",
                "position": 114
            },
            {
                "token": "系",
                "start_offset": 159,
                "end_offset": 160,
                "type": "CJK",
                "position": 115
            },
            {
                "token": "我",
                "start_offset": 160,
                "end_offset": 161,
                "type": "CJK",
                "position": 116
            },
            {
                "token": "11155",
                "start_offset": 163,
                "end_offset": 168,
                "type": "number",
                "position": 117
            },
            {
                "token": "a",
                "start_offset": 168,
                "end_offset": 169,
                "type": "word",
                "position": 118
            },
            {
                "token": "55",
                "start_offset": 169,
                "end_offset": 171,
                "type": "number",
                "position": 119
            },
            {
                "token": "哈",
                "start_offset": 170,
                "end_offset": 171,
                "type": "CJK",
                "position": 120
            },
            {
                "token": "哈",
                "start_offset": 171,
                "end_offset": 172,
                "type": "CJK",
                "position": 121
            },
            {
                "token": "111",
                "start_offset": 176,
                "end_offset": 179,
                "type": "number",
                "position": 122
            }
        ]
    }
    
测试sub分词器，在这里就是我们自定义的prefix和postfix分词器

    POST http://localhost:9200/products/_analyzer
    
    {
        "analyzer": "prefix",
        "text": "TCC20040112442814525679"
    }
    
    {
        "tokens": [
            {
                "token": "TCC2",
                "start_offset": 0,
                "end_offset": 3,
                "type": "word",
                "position": 0
            },
            {
                "token": "TCC200",
                "start_offset": 0,
                "end_offset": 5,
                "type": "word",
                "position": 1
            }
        ]
    }
    
    POST http://localhost:9200/products/_analyzer

    {
        "analyzer": "postfix",
        "text": "TCC20040112442814525679"
    }
    
    {
        "tokens": [
            {
                "token": "525679",
                "start_offset": 17,
                "end_offset": 22,
                "type": "word",
                "position": 0
            },
            {
                "token": "5679",
                "start_offset": 19,
                "end_offset": 22,
                "type": "word",
                "position": 1
            }
        ]
    }
    