# Explorando Marte

O objetivo do projeto é movimentar as Sondas inicializadas por um plano cartesiano (planalto de Marte).

## Rotas utilizadas:

### Inicializando o Planalto

Inicializa um planalto retangular de tamanho x / y positivo, sendo que o canto inferior direito é (0, 0) e o superior esquerdo é o x / y enviados.

**Caminho:** /plateau/init

**Método:** POST

**Retorno:** 201 CREATED

```json
{
  "message": "Created",
  "code": 201
}
```

**Parâmetros a serem enviados:**

* [int] x - maior ponto x no planalto
* [int] y - maior ponto y no planalto

**Tratamento de erro:**

* PlateauNotRectangleException (400 BAD REQUEST) - caso x seja igual a y
* NegativeCoordinateException (400 BAD REQUEST) - caso x ou y tenham valores negativos
* MethodArgumentTypeMismatchException (400 BAD REQUEST) - caso x ou y não sejam enviados

### Recuperando o estado do Planalto

Recupera o estado do planalto, que contém as coordenadas de todas as sondas inicializadas.

**Caminho:** /plateau/status

**Método:** GET

**Retorno:** 200 OK

```json
{
  "plateau": {
    "probe_coordinates": [
    	"1 2 N",
    	"3 3 E"
    ]
  }
}
```

**Tratamento de erro:**

* PlateauNotFoundException (202 ACCEPT) - caso o planalto não tenha sido inicializado

### Inicializando as Sondas

Inicializa uma sonda a cada requisição e a insere no planalto de acordo com a posição x e y, e a aponta para direção enviada.

**Caminho:** /probe/init

**Método:** POST

**Retorno:** 201 CREATED

```json
{
  "message": "Created",
  "code": 201
}
```

**Parâmetros a serem enviados:**

* [int] x - ponto x onde a sonda será inserida
* [int] y - ponto y onde a sonda será inserida
* [char] direction - direção para onde a sonda estará apontando
  * N - norte
  * S - sul
  * E - leste
  * W - oeste
* [string] mSequence - sequência de movimentos a serem realizados pela sonda
  * R - sonda vira para a direita (muda a direção)
  * L - sonda vira para a esquerda (muda a direção)
  * M - sonda move 1 ponto no plano cartesiano de acordo com a direção que está apontada

**Tratamento de erro:**

* PlateauNotFoundException (202 ACCEPT) - caso o planalto não tenha sido inicializado
* ProbeOutPlateauException (400 BAD REQUEST) - caso x ou y for maior que x e y do planalto
* NegativeCoordinateException (400 BAD REQUEST) - caso x ou y tenham valores negativos
* ProbeSequenceException (400 BAD REQUEST) - caso algum movimento da sequência enviada seja inválido
* InvalidDirectionException (400 BAD REQUEST) - caso a direção enviada seja inválida
* MethodArgumentTypeMismatchException (400 BAD REQUEST) - caso x ou y não sejam enviados
* MissingServletRequestParameterException (400 BAD REQUEST) - caso mSequence ou direction não sejam enviados

### Movendo as Sondas

Move as sondas na ordem em que foram inicializadas.

**Caminho:** /probe/move

**Método:** GET

**Retorno:** 200 OK

```json
{
  "message": "OK",
  "code": 200
}
```

**Tratamento de erro:**

* PlateauNotFoundException (202 ACCEPT) - caso o planalto não tenha sido inicializado
* ProbeNotFoundException (202 ACCEPT) - caso nenhuma sonda tenha sido inicializada
* ProbeOutPlateauException (400 BAD REQUEST) - caso, após a movimentação, x ou y for maior que x e y do planalto