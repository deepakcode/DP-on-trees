# DP-on-trees

### 

Given a tree with N nodes and N-1 edges, calculate the maximum sum of the node values from root to any of the leaves without re-visiting any node. 

![image](https://user-images.githubusercontent.com/13814143/185197407-55d15ee2-d1d0-44dc-a5e0-5202f1e5be7f.png)

All the paths are marked by different colors : 

Path 1(red, 3-2-1-4) : sum of all node values = 10 

Path 2(orange, 3-2-1-5) : sum of all node values = 11 

Path 3(yellow, 3-2-3) : sum of all node values = 8 

Path 4(green, 3-1-9-9) : sum of all node values = 22 

Path 5(violet, 3-1-9-8) : sum of all node values = 21 

Path 6(pink, 3-10-1) : sum of all node values = 14 

Path 7(blue, 3-10-5) : sum of all node values = 18 

Path 8(brown, 3-10-3) : sum of all node values = 16 

**The answer is 22, as Path 4 has the maximum sum of values of nodes in its path from a root to leaves.** 
