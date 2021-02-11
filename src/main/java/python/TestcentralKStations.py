import unittest

class TestcentralKStations(unittest.TestCase):

    def test_three(self):
      expected = ["King's Cross St. Pancras", "Baker Street", "Embankment"]
      self.assertEqual(centralKStations(3),expected)

    def test_five(self):
      expected = ["King's Cross St. Pancras", "Baker Street", "Embankment", "Liverpool Street", "Moorgate"]
      self.assertEqual(centralKStations(5),expected)
    
    def test_zero(self):
      self.assertEqual(centralKStations(0),[])

    def test_invalid(self):
      self.assertEqual(centralKStations(-1),[])
