#!/usr/bin/env python3

import sys

if len(sys.argv) > 1:
    print(sys.argv)
    for arg in sys.argv:
        print(arg, len(arg))
