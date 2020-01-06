TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt
TARGET = snake
SOURCES += main.cpp \
    exo1.cpp

HEADERS +=\ \
    exo1.h

LIBS +=-lglut

include( ../examples.pri )
