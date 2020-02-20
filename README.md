# Challenge
La url para probar la app es: https://demoinvest.herokuapp.com/invest.html Sólo hay que ingresar el input y dar al boton de Process

INPUT

{
	"initialBalances": {
		"cash": 1000,
		"issuers":[
			{
			"issuerName":"GBM",
			"totalShares": 10,
			"sharePrice": 10
			}
		]
	}
	,
	"orders": [
		{
			"timestamp": "1571325625",
			"operation":"BUY",
			"IssuerName":"GBM",
			"TotalShares": 10,
			"SharePrice": 10
		}
		,
		{
			"timestamp": "1571327535",
			"operation":"SELL",
			"IssuerName":"GBM",
			"TotalShares": 5,
			"SharePrice": 10
		}
	]
}

OUTPUT 

{
    "cash": 950.0,
    "issuers": [
        {
            "issuerName": "GBM",
            "totalShares": 15,
            "sharePrice": 10.0
        }
    ],
    "bussinessErrors": []
}

# Soporte para varios issuers
Se procesa cada orden de acuerdo al IssuerName

INPUT

{
	"initialBalances": {
		"cash": 1000,
		"issuers":[
			{
			"issuerName":"GBM",
			"totalShares": 10,
			"sharePrice": 10
			},
			{
			"issuerName":"OTRA",
			"totalShares": 10,
			"sharePrice": 10
			}
		]
	}
	,
	"orders": [
		{
			"timestamp": "1571325625",
			"operation": "SELL",
			"IssuerName": "GBM",
			"TotalShares": 9,
			"SharePrice": 10
		}
	]
}

OUTPUT

{
    "cash": 1090.0,
    "issuers": [
        {
            "issuerName": "GBM",
            "totalShares": 1,
            "sharePrice": 10.0
        },
        {
            "issuerName": "OTRA",
            "totalShares": 10,
            "sharePrice": 10.0
        }
    ],
    "bussinessErrors": []
}

# Soporte para cuando vendes todo tu stock
Se remueve de la lista del issuers

INPUT

{
	"initialBalances": {
		"cash": 1000,
		"issuers":[
			{
			"issuerName":"GBM",
			"totalShares": 10,
			"sharePrice": 10
			}
		]
	}
	,
	"orders": [
		{
			"timestamp": "1571325625",
			"operation": "SELL",
			"IssuerName": "GBM",
			"TotalShares": 10,
			"SharePrice": 10
		}
	]
}

OUTPUT

{
    "cash": 1100.0,
    "issuers": [],
    "bussinessErrors": []
}

# Soporte para compras por primera vez
Si la orden es una compra, no importa que no tengas stock, se agrega a la lista de issuers.

INPUT

{
	"initialBalances": {
		"cash": 1000,
		"issuers":[
			{
			"issuerName":"GBM",
			"totalShares": 10,
			"sharePrice": 10
			}
		]
	}
	,
	"orders": [
		{
			"timestamp": "1571325625",
			"operation": "BUY",
			"IssuerName": "OTRA",
			"TotalShares": 10,
			"SharePrice": 10
		}
	]
}

OUTPUT

{
    "cash": 900.0,
    "issuers": [
        {
            "issuerName": "GBM",
            "totalShares": 10,
            "sharePrice": 10.0
        },
        {
            "issuerName": "OTRA",
            "totalShares": 10,
            "sharePrice": 10.0
        }
    ],
    "bussinessErrors": []
}

*Se ha implemenatado operaciones duplicadas y el formato de respuesta 
