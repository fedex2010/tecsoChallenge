REPO: https://github.com/fedex2010/tecsoChallenge.git

URL para testear maquina: https://tecsotest.herokuapp.com/api/currentAccounts/test

SUPUESTOS y/o PROBLEMAS
- Me confundi un poco con el manejo de los fondos de la cuenta, me mareé entre los conceptos de CREDIT / DEBIT, con extraccion y deposito, junto con el hecho de que la cuenta podría tomar un "descubierto", por lo que opte por definir que cada cuenta tuviera dos saldos, un saldo crediticio y un saldo en efectivo

- No supe interpretar lo de impedir el borrado de los movimientos, no sabia si se limitaba a no definir un endpoint/controller o a protejer
los elementos de la relacion desde hibernate, opte por el segundo approach, si se ejecuta un delete sobre la relacion de movimientos el mismo mismo se ejecuta reemplazandose por un update, donde los elementos a ser borrados solamente son marcados con un estado inactive

- No llegue a hacer la aplicacion react que se iba a encargar de consumir la API, me hubiera gustado armar la UI pero no llegue con los tiempos
--------------------------------------
Endpoints

listar todas las cuentas
curl -X GET "https://tecsotest.herokuapp.com/api/currentAccounts/"   

obtener cuenta
curl -X GET "https://tecsotest.herokuapp.com/api/currentAccounts/{cuentaId}"   

borrar cuenta
curl -X DELETE "https://tecsotest.herokuapp.com/api/currentAccounts/{cuentaId}"   

crear cuenta
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"accountMoneyType":"PESO","initialMoney":2123}' https://tecsotest.herokuapp.com/api/currentAccounts/  
(initialMoney es opcional)

listar movimientos
curl -X GET "https://tecsotest.herokuapp.com/api/movements"   

listar movimientos de una cuentas
curl -X GET "https://tecsotest.herokuapp.com/api/account/{cuentaId}/movements"   

borrado SUAVE de los movimientos
curl -X DELETE "https://tecsotest.herokuapp.com/api/movements/{movementId}"   

crear movimiento

curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"description":"una descript","importe":2123,"type":"DEBIT","operationType":"DEPOSIT"}' https://tecsotest.herokuapp.com/api/account/{cuentaId}/movements/

curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"description":"una descript","importe":2124,"type":"DEBIT","operationType":"EXTRACTION"}' https://tecsotest.herokuapp.com/api/account/{cuentaId}/movements/

curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"description":"una descript","importe":2123,"type":"CREDIT"}' https://tecsotest.herokuapp.com/api/account/{cuentaId}/movements/


