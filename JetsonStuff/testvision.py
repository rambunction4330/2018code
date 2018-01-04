import cv2
from threading import Thread
import numpy as np


cap = cv2.VideoCapture(0)
while(1):
    ret, frame = cap.read()
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    cvt = cv2.inRange(hsv, np.array([0, 0, 0]), np.array([255, 255, 255]))
    im2, contours, hierarchy = cv2.findContours(cvt, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    cnt = contours[0]
    moms = cv2.moments(cnt)
    if moms["m00"] != 0:
        cx = int(moms['m10'] / moms['m00'])
        cy = int(moms['m01'] / moms['m00'])
    else:
        cx, cy = 0, 0
    area = cv2.contourArea(cnt)



