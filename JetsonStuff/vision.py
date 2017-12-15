import numpy as np
import cv2
import socketserver
import threading

cap = cv2.VideoCapture(0)
cv2.namedWindow('frame')

def nothing(x):
    pass
lowh = 0
lowl = 0
lows = 0
highh = 255
highl = 255
highs = 255
cv2.createTrackbar('lowh', 'frame', 0, 255, nothing)
cv2.createTrackbar('lowl', 'frame', 0, 255, nothing)
cv2.createTrackbar('lows', 'frame', 0, 255, nothing)
cv2.createTrackbar('highh', 'frame', 0, 255, nothing)
cv2.createTrackbar('highl', 'frame', 0, 255, nothing)
cv2.createTrackbar('highs', 'frame', 0, 255, nothing)
while(1):
    # Capture frame-by-frame
    ret, frame = cap.read()

    h = cv2.getTrackbarPos('lowh', 'frame')
    l = cv2.getTrackbarPos('lowl', 'frame')
    s = cv2.getTrackbarPos('lows', 'frame')
    hh = cv2.getTrackbarPos('highh', 'frame')
    ll = cv2.getTrackbarPos('highl', 'frame')
    ss = cv2.getTrackbarPos('highs', 'frame')
    # Our operations on the frame come here
    hls = cv2.cvtColor(frame, cv2.COLOR_BGR2HLS)
    low = np.array([h+10, l+10, s+10])
    high = np.array([hh+10, ll+10, ss+10])
    cvt = cv2.inRange(hls, low, high)
    res = cv2.bitwise_and(frame,frame, mask= cvt)
    # Display the resulting frame
    contours, im2, hierarchy = cv2.findContours(cvt, cv2.RETR_LIST, cv2.CHAIN_APPROX_NONE)
    minArea = 300

    cnt = contours[0]
    moms = cv2.moments(cnt)
    print(moms['m00'])
    print(moms['m01'])
    cx = int(moms['m10']/moms['m00'])
    cy = int(moms['m01']/moms['m00'])
    for i in range(len(contours)):

        area = cv2.contourArea(cnt)
        if area < minArea:
            continue
        rect = cv2.boundingRect(contours[i])
        width = rect.width
        height = rect.height
        aspectRatio = height / width
        perfectAS = 2.5
        AStol = .5
        if aspectRatio < perfectAS - AStol or aspectRatio > perfectAS + AStol:
            continue
        rectang = area / (width * height)
        if rectang < .7:
            continue
        center = (moms.m10 / moms.m00, moms.m01 / moms.m00)
        print(len(cnt))


    cv2.imshow('frame', cvt)
    cv2.imshow('dab', res)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# When everything done, release the capture
cap.release()
cv2.destroyAllWindows()
