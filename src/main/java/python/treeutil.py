"""# Funções"""

from jgrapht.algorithms.shortestpaths import yen_k_loopless
############################
### Funções auxiliares para árvores enraizadas
def parent (v,rtree):
  inedges = list(rtree.inedges_of(v))
  if len(inedges) == 1:
    return rtree.edge_source(inedges[0])
  else: # todo vértice tem apenas um predecessor em uma árvore enraizada
    return None

def children (v,tree):
  oute = tree.outedges_of(v)
  return [tree.edge_target(e) for e in oute]

def is_root(v,tree):
  return list(tree.inedges_of(v))==[]

def is_leaf(v,tree):
  return list(tree.outedges_of(v))==[]
  
def level(tree,root,v):
  return len(next(yen_k_loopless(tree,root,v,1)).edges)

def dist(g,v1,v2):
  return len(next(yen_k_loopless(g,v1,v2,1)).edges)

def get_rootedTree(tree,root):
  if not is_tree(tree):
    return None
  # cria instância do grafo orientado com opção dag=True
  rtree = jgrapht.create_graph(dag=True,weighted=False)
  # adiciona vértices no grafo
  rtree.add_vertices_from(tree.vertices)
  # adiciona arcos no grafo a partir das arestas de tree
  # considerando como source o terminal com menor nível (distância de root)
  for e in tree.edges:
    s = tree.edge_source(e)
    t = tree.edge_target(e)
    if level(tree,root,s) < level(tree,root,t):
      rtree.add_edge(s,t)
    else:
      rtree.add_edge(t,s)
  return rtree

# Constroi uma árvore de busca em largura
# Algoritmo BFS visto nas notas de aula, retornando árvore ao invés da função predecessor
# Retorna uma árvore orientada, a função nível e a função tempo
from random import randint
def bfs (g,root):
  if not root in g.vertices:
    return None,None,None
  i = 0
  Q = [] # Lista que representa a fila Q
  i = i+1
  visited = [root] # Lista com vértices que foram pintados
  l = {}
  l[root] = 0
  t = {}
  t[root]=i
  Q = [root]
  tree = jgrapht.create_graph(directed=True,weighted=False,dag=True)
  tree.add_vertex(root)
  while Q != []:
    x = Q[0] # x é o vértice da cabeça de Q
    neighbors = [g.opposite(e,x) 
                  for e in g.edges_of(x) if g.opposite(e,x) not in visited]
    if neighbors != []: # x ainda tem vizinhos não pintados
      # escolhe um dos vizinhos y aleatoriamente
      y = neighbors[randint(0,len(neighbors)-1)]
      i = i+1
      visited.append(y)
      tree.add_vertex(y)
      tree.add_edge(x,y)
      l[y] = l[x]+1
      t[y] = i
      Q.append(y) #adiciona y ao fim da fila
    else:
      Q.pop(0) # remove x da cabeça da fila Q
  return tree,l,t

# Constroi uma árvore de busca em profundidade
# Algoritmo DFS apresentado nas notas de aula
# Retorna uma árvore DFS orientada e as funções tempo
from random import randint
def dfs (g,root):
  if not root in g.vertices:
    return None,None,None
  f = {} # função tempo de inclusão na árvore
  t = {} # função tempo de saída da pilha
  i = 0
  S = [] # Lista que representa a pilha S
  i = i+1
  visited = [root] # Lista com vértices que foram pintados
  f[root] = i
  S.append(root)
  tree = jgrapht.create_graph(directed=True,weighted=False,dag=True)
  tree.add_vertex(root)
  while S != [] :
    x = S[0] # x é o topo da pilha S
    neighbors = [g.opposite(e,x) for e in g.edges_of(x) if g.opposite(e,x) not in visited]
    i = i+1
    if neighbors != []:
      # escolhe um dos vizinhos y aleatoriamente
      y = neighbors[randint(0,len(neighbors)-1)]
      visited.append(y)
      tree.add_vertex(y)
      tree.add_edge(x,y)
      f[y] = i
      S.insert(0,y) #adiciona y ao topo de S
    else:
      t[x] = i
      S.pop(0) #remove x do topo
  return tree,f,t
  
