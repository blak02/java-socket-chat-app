# Java Socket Chat Application

A desktop chat application built with Java Swing and TCP sockets. The project demonstrates client-server networking, multi-client handling, message history, user discovery, message search, and a graphical desktop interface.

## Features

- TCP client-server architecture
- Multiple simultaneous clients using server-side threads
- Swing-based desktop GUI
- Connected-user list
- Direct messaging between users
- Conversation history
- Message search
- Timestamped messages

## Project Structure

```text
src/
├── ChatApplicationServer.java
├── ChatApplicationGUI.java
├── ChatClient.java
└── Message.java
```

## Requirements

- JDK 8 or newer

## Build

From the repository root:

```bash
javac -d out src/*.java
```

## Run

Start the server first:

```bash
java -cp out ChatApplicationServer
```

Then open another terminal and start a client:

```bash
java -cp out ChatApplicationGUI
```

Run the client command in additional terminals to connect multiple users. By default, the application connects to `localhost` on TCP port `9999`.

## Technical Notes

The server stores connected clients and message history in memory. The communication protocol is intentionally lightweight and designed for a local educational demonstration rather than production deployment.

## Possible Improvements

- Persistent message storage
- User authentication
- TLS encryption
- More robust message serialization
- Thread-safe shared collections for heavy concurrent use
- Configurable host and port

#Author 
Kemal Berkay Lak
Wrocław University of Science and Technology, Electronics and Computer Engineering
