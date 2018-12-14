# Übergangstabelle
|        | 0    | 1     |
| :----: |:----:| :----:|
|__->q0__| q1   | q0    | 
| __q1__ | q1   | q2    |
| __q2*__| q3   | q4    |
| __q3*__| q3   | q2    |
| __q4__ | q5   | q4    |
| __q5__ | q5   | q3    |

# Parsing Tree
![parsing ree](de/tetrisiq/uebs/A6/Tree.png)

# Graphviz String
```Graphviz
digraph {
         "q0"[color="green",style = filled];
         "q1"[color="",style = filled];
         "q2"[color="red",style = filled]
         q0 -> q1[label="0",weight="0.2"];
         q0 -> q0[label="1",weight="0.2"];
         q1 -> q0[label="0",weight="0.2"];
         q1 -> q2[label="1",weight="0.2"];
         q2 -> q0[label="0",weight="0.2"]
         q2 -> q0[label="1",weight="0.2"]
     } 
```

# Automat
- Starzustände sind grün makiert
- Endzustände sind rot makiert
  
![DEA](de/tetrisiq/uebs/A6/DEA.png)

