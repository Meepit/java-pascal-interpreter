import java.util.ArrayList;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    private ArrayList<TokenType> highPrecOperations = new ArrayList<TokenType>();
    private ArrayList<TokenType> lowPrecOperations = new ArrayList<TokenType>();

    Parser(Lexer lexer){
        this.lexer = lexer;
        this.currentToken = this.lexer.getNextToken();
        lowPrecOperations.add(TokenType.PLUS);
        lowPrecOperations.add(TokenType.MINUS);

        highPrecOperations.add(TokenType.MUL);
        highPrecOperations.add(TokenType.DIV);
    }

    private void error() throws RuntimeException{
        throw new RuntimeException("Error parsing input");
    }

    private void eat(TokenType tokenType){
        //Confirms the current token matches a token in a valid expression
        //If true increment the currentToken else throw error
        if(this.currentToken.getType() == tokenType){
            this.currentToken = this.lexer.getNextToken();
        } else {
            error();
        }
    }

    private AST program(){
        //program : compound_statement DOT
        AST node = compoundStatement();
        eat(TokenType.DOT);
        return node;
    }

    private AST compoundStatement(){
        //compound_statement : BEGIN statement_left END
        eat(TokenType.BEGIN);
        ArrayList<AST> nodes = statementList();
        eat(TokenType.END);
        Compound root = new Compound();
        for(AST node : nodes){
            root.getChildren().add(node);
        }
        return root;
    }

    private ArrayList<AST> statementList(){
        //statement_list : statement
        //               || statement SEMI statement_list
        AST node = statement();

        ArrayList<AST> results = new ArrayList<AST>();
        results.add(node);

        while(this.currentToken.getType() == TokenType.SEMI){
            eat(TokenType.SEMI);
            results.add(statement());
        }

        if(this.currentToken.getType() == TokenType.SEMI){
            error();
        }

        return results;
    }

    private AST statement(){
        //statement : compound_statement
        //          : assignment_statement
        //          : empty

        AST node = new AST();
        if(this.currentToken.getType() == TokenType.BEGIN){
             node = compoundStatement();
        } else if(this.currentToken.getType() == TokenType.ID){
             node = assignmentStatement();
        } else {
             node = empty();
        }
        return node;
    }

    private AST assignmentStatement(){
        //assignment_statement : variable ASSIGN expr

        Var left = variable();
        Token token = this.currentToken;
        eat(TokenType.ASSIGN);
        AST right = expr();
        Assign node = new Assign(left, token, right);
        return node;
    }

    private Var variable(){
        //variable : ID
        Var node = new Var(this.currentToken);
        eat(TokenType.ID);
        return node;
    }

    private AST empty(){
        return new NoOp();
    }

    private AST term(){
        // term : factor((MUL | DIV) factor)*
        AST node = factor();

        while(this.highPrecOperations.contains(this.currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.MUL){
                eat(TokenType.MUL);
            } else if(token.getType() == TokenType.DIV){
                eat(TokenType.DIV);
            }

            node = new BinOP(node, token, factor());
        }
        return node;

    }

    private AST factor(){
        //factor : INTEGER | LPAREN expr RPAREN

        AST node = new AST();
        Token token = this.currentToken;
        if(token.getType() == TokenType.PLUS){
            eat(TokenType.PLUS);
            node = new UnaryOp(token, factor());
            return node;
        } else if(token.getType() == TokenType.MINUS){
            eat(TokenType.MINUS);
            node = new UnaryOp(token, factor());
            return node;
        } else if(token.getType() == TokenType.INTEGER){
            eat(TokenType.INTEGER);
            node = new Num(token);
            return node;
        } else if(token.getType() == TokenType.LPAREN){
            eat(TokenType.LPAREN);
            node = expr();
            eat(TokenType.RPAREN);
            return node;
        } else {
            node = variable();
            return node;
        }
    }

    private AST expr(){
        //Arithmetic expression parser / interpreter.
        // expr : term((PLUS | MINUS) term)*
        // term : factor((MUL | DIV) factor)*
        // factor : INTEGER | LPAREN expr RPAREN
        AST node = term();

        while(this.lowPrecOperations.contains(currentToken.getType())){
            Token token = currentToken;
            if(token.getType() == TokenType.PLUS){
                eat(TokenType.PLUS);
            } else if(token.getType() == TokenType.MINUS){
                eat(TokenType.MINUS);
            }

            node = new BinOP(node, token, term());
        }
        return node;
    }

    public AST parse(){
        AST node = program();
        if(this.currentToken.getType() != TokenType.EOF){
            error();
        }

        return node;
    }
}
