TEMPLATE = app
TARGET   = solarsystem

CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt

HEADERS  = simpleViewer.h \
    sphere.h
SOURCES  = simpleViewer.cpp main.cpp \
    sphere.cpp

LIBS +=-lglut
include( ../examples.pri )
