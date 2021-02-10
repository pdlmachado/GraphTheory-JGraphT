import unittest

def adjacency_matriz (g):
  # escreva aqui o seu código
  return []

import jgrapht

class TestStringMethods(unittest.TestCase):

    def test_empty(self):
      g1 = jgrapht.create_graph(directed=False,weighted=False)
      expected = []
      self.assertEqual(adjacency_matriz (g1),expected)
    
    def test_notempty(self):
        g1 = jgrapht.create_graph(directed=False,weighted=False)
        g1.add_vertices_from([0,1,2,3])
        g1.add_edges_from([(0,1),(0,2),(0,3),(1,3),(2,3)])
        expected = [[0,1,1,1],[1,0,0,1],[1,0,0,1],[1,1,1,0]]
        self.assertEqual(adjacency_matriz (g1),expected)


if __name__ == '__main__':
    unittest.main()
