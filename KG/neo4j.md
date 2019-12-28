## Cypher
1. 查询多个节点时，cypher没有类似 `(n:Female | Male) ` 的语法，只能通过 `UNION` 连接指定Label的语句，或者不指定Label在`where`后面加限定
```
MATCH (n:Actor) WHERE n.name IN ['菊花台','周杰伦'] RETURN n.name as name, Labels(n) as type UNION MATCH (n:Song) WHERE n.name IN ['菊花台','周杰伦'] RETURN n.name as name, Labels(n) as type
```
```
profile MATCH (user) WHERE user.name IN ['周杰伦', '菊花台'] and (user:Song or user:Actor)   RETURN user.name, Labels(user) as type
```
前者:通过NodeIndexSeek，返回时间11ms，后者通过NodeByLabelScan，返回时间2734ms。

1. 

