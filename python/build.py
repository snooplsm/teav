import xml.etree.ElementTree as ET
import sqlite3
import os
import shutil
import time as tk
from datetime import datetime
from datetime import timedelta

schedule = ET.parse('schedule.xml')
scheduleRoot = schedule.getroot()
shutil.rmtree("target",True)
os.makedirs("target")
conn = sqlite3.connect("target/tvdb.db")
c = conn.cursor()
c.execute("""CREATE TABLE show (
        id INT PRIMARY KEY,
		name VARCHAR(255),
        status VARCHAR(20),
		country VARCHAR(20)
);""")
c.execute("""CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US')""");
c.execute("""INSERT INTO "android_metadata" VALUES ('en_US')""");
c.execute("""CREATE TABLE schedule (		
		name VARCHAR(255),
		id INT,
		day varchar(20),
		time integer,
		
		network VARCHAR(20),
		title VARCHAR(255),
		episode varchar(20),
		link text		
);""")
for day in scheduleRoot.iter('DAY'):
	for time in day.iter('time'):
		showtime = day.attrib['attr'] + " " + time.attrib['attr']
		showStart = datetime.strptime(showtime,"%Y-%m-%d %H:%M")
		showStart = long(tk.mktime(showStart.timetuple()))*1000
		for show in time.iter('show'):
			c.execute("INSERT INTO schedule(name,id,day,time,network,title,episode,link) values(?,?,?,?,?,?,?,?)",
				(show.attrib['name'],show.find('sid').text,day.attrib['attr'],showStart,show.find('network').text,show.find('title').text,show.find('ep').text,show.find('link').text))
conn.commit();
tree = ET.parse('shows.xml')
root = tree.getroot()
for neighbor in root.iter('show'):
	#print neighbor.find('name').text
	print neighbor.find('name').text[0:1]
	c.execute("INSERT INTO show(id,name,country,status) values(?,?,?,?)",(neighbor.find('id').text,neighbor.find('name').text,neighbor.find('country').text,neighbor.find('status').text))			
conn.commit();
conn.close();
