# Use official Node.js image as a base
FROM node:18 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular app for production
RUN npm run build --prod

# Serve the app using a simple server
FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Expose the port for the Angular app
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
