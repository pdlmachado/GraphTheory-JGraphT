import jgrapht
# Constroi uma árvore de busca em profundidade
def dfs (g,root):
  visited = [root]
  S = [root]
  tree = jgrapht.create_graph(directed=True,weighted=False,dag=True)
  tree.add_vertex(root)
  while S != [] :
    x = S[0] 
    neighbors = [g.opposite(e,x) for e in g.edges_of(x) if g.opposite(e,x) not in visited]
    if neighbors != []:
      y = neighbors[0]
      visited.append(y)
      S.insert(0,y)
      tree.add_vertex(y)
      tree.add_edge(x,y)
    else:
      S.pop(0)
  return tree

# Constroi uma árvore de busca em largura
def bfs (g,root):
  visited = [root]
  l = {}
  l[root] = 0
  Q = [root]
  tree = jgrapht.create_graph(directed=True,weighted=False,dag=True)
  tree.add_vertex(root)
  while Q != [] :
    x = Q[0] 
    neighbors = [g.opposite(e,x) for e in g.edges_of(x) if g.opposite(e,x) not in visited]
    if neighbors != []:
      y = neighbors[0]
      visited.append(y)
      Q.append(y)
      tree.add_vertex(y)
      tree.add_edge(x,y)
      l[y] = l[x]+1
    else:
      Q.pop(0)
  return tree,l

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
  
