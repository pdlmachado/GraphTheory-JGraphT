
# Importando a JgraphT 
import jgrapht
from jgrapht.algorithms.shortestpaths import yen_k_loopless
from jgrapht.properties import is_tree
from random import randint
from jgrapht.views import as_undirected
from jgrapht import create_graph
from jgraphtutil import dist

"""# Funções para Árvore Enraizada"""

# Verifica se um grafo é uma árvore enraizada
def is_rootedTree(tree):
  root = list(filter(lambda v: list(tree.inedges_of(v)) == [],tree.vertices))
  if len(root) == 1:
    notroot = [v for v in tree.vertices if v not in root]
    return all(len(list(tree.inedges_of(v)))==1 for v in notroot)
  return False

# Retorna o predecessor (parent) de um vértice v em uma árvore enraizada
def parent (v,rtree):
  if is_rootedTree(rtree):
    inedges = list(rtree.inedges_of(v))
    if len(inedges) == 0:
      return None # root
    else:
      return rtree.edge_source(inedges[0])
  else: # todo vértice tem apenas um predecessor em uma árvore enraizada
    return None

# Retorna uma lista com os sucessores (children) de um vértice v em uma árvore enraizada
def children (v,rtree):
  if is_rootedTree(rtree):
    oute = rtree.outedges_of(v)
    return [rtree.edge_target(e) for e in oute]
  else:
    return None

# Determina se um vértice v é a raiz em uma árvore enraizada
def is_root(v,rtree):
  if (is_rootedTree(rtree)):
    return list(rtree.inedges_of(v))==[]
  else:
    return None

def is_leaf(v,rtree):
  if (is_rootedTree(rtree)):
    return list(rtree.outedges_of(v))==[]
  else:
    return None

# Retorna uma árvore enraizada (dag) para uma árvore, sendo root a raiz 
def get_rootedTree(tree,root):
  if (not is_tree(tree)) or (root not in tree.vertices):
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
    if dist(tree,root,s) < dist(tree,root,t):
      rtree.add_edge(s,t)
    else:
      rtree.add_edge(t,s)
  return rtree

"""# Busca em Largura"""

# Retorna uma árvore de busca em largura
# Algoritmo BFS visto nas notas de aula, retornando árvore ao invés da função predecessor
# Retorna uma árvore orientada, a função nível e a função tempo

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

"""# Busca em Profundidade"""

# Constroi uma árvore de busca em profundidade
# Algoritmo DFS apresentado nas notas de aula
# Retorna uma árvore DFS orientada e as funções tempo
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