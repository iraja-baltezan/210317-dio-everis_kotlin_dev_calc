/*
# dio-210317-everis_kotlin_dev_calc
Introdução ao Kotlin, Proposta de exercício final

## Kotlinc
O código pode ser compilado e executado pelo compilador de linha de comando do Kotlin.

Saiba como instalar o **kotlinc** seguindo as instruções deste endereço:
https://kotlinlang.org/docs/command-line.html

## O exercício

Transcrição do enunciado da propósta de exercício:

>Com os conhecimentos aplicados atnteriormente, vamos criar uma **calculadora** que
>
>- dado **dois valores Float?**
>- e **un número corresponde a operação** (constante),
>- retorne e 
>   - **imprima o resultado como Float**
>   - ou uma **mensagem de erro caso um dos valores seja nulo**.
>
>**Use sua criatividade!** Adicione outras operações, tais como: fatorial, porcentagem, potenciação e etc.

 */

const val OPERADOR_NULO = 0
const val OPERADOR_FINAL = 99

const val OPERADOR_ADICAO = 1
const val OPERADOR_SUBTRACAO = 2
const val OPERADOR_MULTIPLICACAO = 3
const val OPERADOR_DIVISAO = 4


fun main() {
    var operando1 : Float? = null
    var operando2 : Float? = null
    var operador : Int? = null
    var leitura : String? = null

    while(leitura != "s") {
        
        clearConsole()
        println("${Typography.mdash}".repeat(80))
        println("Calculadora")
        println("${Typography.mdash}".repeat(80))
        
        when {
            operando1 == null -> {
                print("Escreva o primeiro número (ou 's' para sair): ")
                leitura = readLine()
                when {
                    leitura.isFloat() -> { operando1 = leitura?.toFloat() }
                    leitura == "s" -> { break }
                    else -> { 
                        if (leitura == null) {
                            println("O valor fornecido é nulo!\nTente outra vez ou escreva 's' para sair.")
                        }
                        else {
                            println("O valor fornecido não é um Float.\nTente outra vez ou escreva 's' para sair.")
                        }
                        continue 
                    }
                }
            }
            operando2 == null -> {
                print("Primeiro número: $operando1\nEscreva o segundo número (ou 's' para sair): ")
                leitura = readLine()
                when {
                    leitura.isFloat() -> { operando2 = leitura?.toFloat() }
                    leitura == "s" -> { break }
                    else -> { 
                        if (leitura == null) {
                            println("O valor fornecido é nulo!\nTente outra vez ou escreva 's' para sair.")
                        }
                        else {
                            println("O valor fornecido não é um Float.\nTente outra vez ou escreva 's' para sair.")
                        }
                        continue 
                    }
                }
            }
            operador == null -> {
                print(
                    """
                    |Operando 1: $operando1
                    |Operando 2: $operando2
                    |Escreva o número da operação 
                    |$OPERADOR_ADICAO) $operando1 + $operando2
                    |$OPERADOR_SUBTRACAO) $operando1 - $operando2
                    |$OPERADOR_MULTIPLICACAO) $operando1 × $operando2
                    |$OPERADOR_DIVISAO) $operando1 ÷ $operando2
                    |R para reiniciar ou S para sair: 
                    """.trimMargin())
                leitura = readLine()
                if (leitura.isInt()) operador = leitura?.toInt()
                if (operador in OPERADOR_NULO..OPERADOR_FINAL) {
                    println(
                        calcular(operando1, operando2, operador as Int)
                    )
                }
                else when (leitura?.toLowerCase()){
                    "s" -> break 
                    "r" -> {
                        operando1 = null
                        operando2 = null
                        continue
                    }
                    else -> continue 
                }
                
                println("Pressione a tecla ENTER ou RETURN para continuar...")
                readLine()
                operador = null
            }
        }
    }
}

fun clearConsole() { for(i in 0..30) println("") }

fun String?.isFloat():Boolean {
    if (this == null) return false
    try { this.toFloat() }
    catch (e:Exception) { return false }
    return true
}

fun String?.isInt():Boolean {
    if (this == null) return false
    try { this.toInt() }
    catch (e:Exception) { return false }
    return true
}

fun calcular(operando1:Float, operando2: Float, operador: Int ):String {
    var resultado : String = "-".repeat(80) + "\nResposta: "

    when (operador){
        OPERADOR_ADICAO        -> resultado += operando1 + operando2
        OPERADOR_SUBTRACAO     -> resultado += operando1 - operando2
        OPERADOR_MULTIPLICACAO -> resultado += operando1 * operando2
        OPERADOR_DIVISAO       -> resultado += if (operando2==0.0f) "divisão por zero" else (operando1 / operando2)
        else -> resultado += "Operação desconhecida"
    }

    resultado += "\n" + "-".repeat(80)
    return resultado
}
