import unittest
# From: https://eli.thegreenplace.net/2011/08/02/python-unit-testing-parametrized-test-cases
class ParametrizedTestCase(unittest.TestCase):
    """ TestCase classes that want to be parametrized should
        inherit from this class.
    """
    def __init__(self, methodName='runTest', param=None):
        super(ParametrizedTestCase, self).__init__(methodName)
        self.param = param

    @staticmethod
    def parametrize(testcase_klass, param=None):
        """ Create a suite containing all tests taken from the given
            subclass, passing them the parameter 'param'.
        """
        testloader = unittest.TestLoader()
        testnames = testloader.getTestCaseNames(testcase_klass)
        suite = unittest.TestSuite()
        for name in testnames:
            suite.addTest(testcase_klass(name, param=param))
        return suite
      
# Test Data
import jgrapht

d1 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d1.add_vertices_from([x for x in range(0,6)])
d1.add_edge(0,1,edge=0)
d1.add_edge(0,2,edge=1)
d1.add_edge(2,3,edge=2)
d1.add_edge(2,4,edge=3)
d1.add_edge(4,5,edge=4)
e_d1 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'r'}
}

d2 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d2.add_vertices_from([x for x in range(0,6)])
d2.add_edge(0,1,edge=0)
d2.add_edge(0,2,edge=1)
d2.add_edge(0,3,edge=2)
d2.add_edge(2,4,edge=3)
d2.add_edge(4,5,edge=4)
e_d2 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'r'}
}


d3 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d3.add_vertex(20)

d4 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d4.add_vertices_from([x for x in range(0,6)])
d4.add_edge(0,1,edge=0)
d4.add_edge(0,2,edge=1)
d4.add_edge(2,3,edge=2)
d4.add_edge(2,4,edge=3)
d4.add_edge(4,5,edge=4)
e_d4 = {
    0 : {'label': 'l'},
    1 : {'label': 'l'},
    2 : {'label': 'l'},
    3 : {'label': 'l'},
    4 : {'label': 'l'}
}

d5 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d5.add_vertices_from([x for x in range(0,6)])
d5.add_edge(0,1,edge=0)
d5.add_edge(1,2,edge=1)
d5.add_edge(2,3,edge=2)
d5.add_edge(3,4,edge=3)
d5.add_edge(4,5,edge=4)
e_d5 = {
    0 : {'label': 'r'},
    1 : {'label': 'r'},
    2 : {'label': 'r'},
    3 : {'label': 'r'},
    4 : {'label': 'r'}
}

d6 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d6.add_vertices_from([x for x in range(1,10)])
d6.add_edge(1,2,edge=0)
d6.add_edge(1,3,edge=1)
d6.add_edge(2,4,edge=2)
d6.add_edge(2,5,edge=3)
d6.add_edge(3,6,edge=4)
d6.add_edge(3,7,edge=5)
d6.add_edge(7,8,edge=6)
d6.add_edge(7,9,edge=7)
e_d6 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'l'},
    5 : {'label': 'r'},
    6 : {'label': 'l'},
    7 : {'label': 'r'}
}

d7 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d7.add_vertices_from([x for x in range(1,11)])
d7.add_edge(1,2,edge=0)
d7.add_edge(1,3,edge=1)
d7.add_edge(2,4,edge=2)
d7.add_edge(2,5,edge=3)
d7.add_edge(3,6,edge=4)
d7.add_edge(3,7,edge=5)
d7.add_edge(7,8,edge=6)
d7.add_edge(7,9,edge=7)
d7.add_edge(8,10,edge=8)
e_d7 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'l'},
    3 : {'label': 'r'},
    4 : {'label': 'l'},
    5 : {'label': 'r'},
    6 : {'label': 'l'},
    7 : {'label': 'r'},
    8 : {'label': 'r'}
}

d8 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d8.add_vertices_from([x for x in range(1,11)])
d8.add_edge(1,2,edge=0)
d8.add_edge(1,3,edge=1)
d8.add_edge(2,4,edge=2)
d8.add_edge(2,5,edge=3)
d8.add_edge(3,6,edge=4)
d8.add_edge(3,7,edge=5)
d8.add_edge(7,8,edge=6)
d8.add_edge(7,9,edge=7)
d8.add_edge(8,10,edge=8)
e_d8 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'r'},
    3 : {'label': 'r'},
    4 : {'label': 'l'},
    5 : {'label': 'r'},
    6 : {'label': 'l'},
    7 : {'label': 'r'},
    8 : {'label': 'r'}
}

d9 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d9.add_vertices_from([x for x in range(1,11)])
d9.add_edge(1,2,edge=0)
d9.add_edge(1,3,edge=1)
d9.add_edge(2,4,edge=2)
d9.add_edge(2,5,edge=3)
d9.add_edge(3,6,edge=4)
d9.add_edge(3,7,edge=5)
d9.add_edge(7,8,edge=6)
d9.add_edge(7,9,edge=7)
d9.add_edge(7,10,edge=8)
e_d9 = {
    0 : {'label': 'l'},
    1 : {'label': 'r'},
    2 : {'label': 'r'},
    3 : {'label': 'r'},
    4 : {'label': 'l'},
    5 : {'label': 'r'},
    6 : {'label': 'l'},
    7 : {'label': 'r'},
    8 : {'label': 'r'}
}

class Test_is_binary_base(ParametrizedTestCase):
  def test_base (self):
    f,d,e,r = self.param
    result = f(d,e)  
    self.assertEqual(result,r)    

params = [[d1,e_d1,True],
          [d2,e_d2,False],
          [d3,{},True],
          [d4,e_d4,False],
          [d5,e_d5,True],
          [d6,e_d6,True],
          [d7,e_d7,True],
          [d8,e_d8,False],
          [d9,e_d9,False]]

