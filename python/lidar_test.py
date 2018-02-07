from networktables import NetworkTables
from lidar_lite import Lidar_Lite
lidar = Lidar_Lite()

NetworkTables.initialize(server='10.43.30.2')


connected = lidar.connect(1)

if connected < -1:
    print "Not Connected"
else:
    print "Connected"

while 1:
    distance = lidar.getDistance()
    print("Distance to target = %s" % (distance))
