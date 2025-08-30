#!/bin/bash
set -e
cd frontend
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
