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
d1.add_vertices_from([x for x in range(0,9)])
d1.add_edge(0,1,edge=0)
d1.add_edge(0,2,edge=1)
d1.add_edge(3,0,edge=2)
d1.add_edge(1,4,edge=3)
d1.add_edge(4,5,edge=4)
d1.add_edge(3,8,edge=5)
d1.add_edge(6,7,edge=6)

d2 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d2.add_vertices_from([x for x in range(0,13)])
d2.add_edge(0,1)
d2.add_edge(4,1)
d2.add_edge(2,4)
d2.add_edge(2,5)
d2.add_edge(3,4)
d2.add_edge(3,5)
d2.add_edge(5,6)
d2.add_edge(7,6)
d2.add_edge(8,7)
d2.add_edge(8,10)
d2.add_edge(9,7)
d2.add_edge(9,10)
d2.add_edge(10,11)
d2.add_edge(12,11)

d3 = jgrapht.create_graph(directed=True, weighted=False, dag=True)
d3.add_vertex(20)


class Test_linhagem_comum(ParametrizedTestCase):
  def test_base (self):
    f,g,s,t,expected = self.param
    result = f(g,s,t)
    self.assertEqual(result,expected)

params = [[d1,0,1,True],   #1
          [d1,5,3,True],   #2
          [d1,5,2,True],   #3
          [d1,1,2,True],   #4
          [d1,4,4,True],   #5
          [d1,0,8,True],   #6
          [d1,6,3,False],  #7
          [d1,1,7,False],  #8
          [d1,7,3,False],  #9
          [d2,3,3,True],   #10
          [d2,2,5,True],   #11
          [d2,4,5,True],   #12
          [d2,9,6,True],   #13
          [d2,1,5,True],   #14
          [d2,11,7,True],  #15
          [d2,6,1,True],   #16
          [d2,2,8,False],  #17
          [d2,4,10,False], #18
          [d2,0,5,False],  #19
          [d3,20,20,True]  #20
           ]
