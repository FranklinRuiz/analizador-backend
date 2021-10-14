package com.als.serviceImpl;

import com.als.model.Request;
import com.als.model.Response;
import com.als.model.Tokens;
import com.als.service.AnalyzerSevice;

import com.als.utils.Lexer;
import com.als.utils.LexerCup;
import com.als.utils.Sintax;
import java_cup.runtime.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;

@Slf4j
@Service
public class AnalyzerServiceImpl implements AnalyzerSevice {

    @Override
    public Response lexicalAnalyzer(Request texto) {
        int cont = 1;
        Response rpta = new Response();
        Lexer lexer = new Lexer(new StringReader(texto.getTexto()));
        String resultado = "<b>LINEA " + cont + "</b><br>";
        while (true) {
            Tokens token = null;
            try {
                token = lexer.yylex();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (token == null) {
                rpta.setCorrecto(true);
                rpta.setRespuesta(resultado);
                break;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "<b>LINEA " + cont + "</b><br>";
                    break;
                case Comillas:
                    resultado += "  [Comillas]  " + lexer.lexeme + "<br>";
                    break;
                case Cadena:
                    resultado += "  [Tipo de dato]  " + lexer.lexeme + "<br>";
                    break;
                case T_dato:
                    resultado += "  [Tipo de dato]  " + lexer.lexeme + "<br>";
                    break;
                case If:
                    resultado += "  [Reservada if ]  " + lexer.lexeme + "<br>";
                    break;
                case Else:
                    resultado += "  [Reservada else ]  " + lexer.lexeme + "<br>";
                    break;
                case Do:
                    resultado += "  [Reservada do ]  " + lexer.lexeme + "<br>";
                    break;
                case While:
                    resultado += "  [Reservada while ]  " + lexer.lexeme + "<br>";
                    break;
                case For:
                    resultado += "  [Reservada while ]  " + lexer.lexeme + "<br>";
                    break;
                case Igual:
                    resultado += "  [Operador igual ]  " + lexer.lexeme + "<br>";
                    break;
                case Suma:
                    resultado += "  [Operador suma ]  " + lexer.lexeme + "<br>";
                    break;
                case Resta:
                    resultado += "  [Operador resta ]  " + lexer.lexeme + "<br>";
                    break;
                case Multiplicacion:
                    resultado += "  [Operador multiplicacion ]  " + lexer.lexeme + "<br>";
                    break;
                case Division:
                    resultado += "  [Operador division ]  " + lexer.lexeme + "<br>";
                    break;
                case Op_logico:
                    resultado += "  [Operador logico ]  " + lexer.lexeme + "<br>";
                    break;
                case Op_incremento:
                    resultado += "  [Operador incremento ]  " + lexer.lexeme + "<br>";
                    break;
                case Op_relacional:
                    resultado += "  [Operador relacional ]  " + lexer.lexeme + "<br>";
                    break;
                case Op_atribucion:
                    resultado += "  [Operador atribucion ]  " + lexer.lexeme + "<br>";
                    break;
                case Op_booleano:
                    resultado += "  [Operador booleano ]  " + lexer.lexeme + "<br>";
                    break;
                case Parentesis_a:
                    resultado += "  [Parentesis de apertura ]  " + lexer.lexeme + "<br>";
                    break;
                case Parentesis_c:
                    resultado += "  [Parentesis de cierre ]  " + lexer.lexeme + "<br>";
                    break;
                case Llave_a:
                    resultado += "  [Llave de apertura ]  " + lexer.lexeme + "<br>";
                    break;
                case Llave_c:
                    resultado += "  [Llave de cierre ]  " + lexer.lexeme + "<br>";
                    break;
                case Corchete_a:
                    resultado += "  [Corchete de apertura ]  " + lexer.lexeme + "<br>";
                    break;
                case Corchete_c:
                    resultado += "  [Corchete de cierre ]  " + lexer.lexeme + "<br>";
                    break;
                case Main:
                    resultado += "  [Reservada main ]  " + lexer.lexeme + "<br>";
                    break;
                case P_coma:
                    resultado += "  [Punto y coma ]  " + lexer.lexeme + "<br>";
                    break;
                case Identificador:
                    resultado += "  [Identificador ]  " + lexer.lexeme + "<br>";
                    break;
                case Numero:
                    resultado += "  [Numero ]  " + lexer.lexeme + "<br>";
                    break;
                case ERROR:
                    resultado += "  [Simbolo no definido]<br>";
                    break;
                default:
                    resultado += "  [ " + lexer.lexeme + " ]<br>";
                    break;
            }
        }
        return rpta;
    }

    @Override
    public Response syntacticAnalyzer(Request texto) {
        Response rpta =  new Response();
        Sintax s = new Sintax(new LexerCup(new StringReader(texto.getTexto())));
        try {
            s.parse();
            rpta.setCorrecto(true);
            rpta.setRespuesta("Analisis realizado correctamente");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            rpta.setCorrecto(false);
            rpta.setRespuesta("Error de sintaxis. Linea: " + (sym.right + 1) + ", Texto: \"" + sym.value + "\"");
        }
        return rpta;
    }
}
