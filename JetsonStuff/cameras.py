from cscore import CameraServer, UsbCamera

cs = CameraServer.getInstance()
cs.enableLogging()

usb1 = cs.startAutomaticCapture(dev=0)
usb1.setResolution(1280, 720)
usb2 = cs.startAutomaticCapture(dev=1)
usb2.setResolution(1920,1080)

cs.waitForever()

