# BinTreeParser

Exempel på rekursiv medåkning: parsa och traversera binära träd

Författare: Per Austrin

## Grammatik

Rekursiv medåkning görs enligt följande LL-grammatik i BNF:
```
 <BinTree> ::= LEAF LPAR NUM RPAR | BRANCH LPAR <BinTree> COMMA <BinTree> RPAR
```
där: 
- `LEAF` är `leaf`
- `BRANCH` är `branch`
- `NUM` är heltal
- `LPAR` är `(` och `RPAR` är `)`
- `COMMA` är `,`

## Kompilering och körning

Parsar binärträdet och skriver sedan ut det.

Provkörning från terminal på fil `test.in`:
```shell
javac *.java
java Main < test.in
```
