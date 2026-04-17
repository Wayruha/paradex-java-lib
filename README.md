# Paradex Java Lib

A lightweight Java library designed to interface with the Paradex decentralized exchange (DEX). This bridge provides a seamless way to trigger crypto market actions via Java endpoints, enabling programmatic trading and account management.

## Features

* **Order/Position Management**: Create and cancel orders/positions on the Paradex market.
* **Balance Verification**: Programmatically check account balances and asset positions.
* **API Integration**: Simplified wrapper for Paradex's REST API.
* **Websocket Update**: Subscribe to real-time order books, trades, and account updates
* **Java Native**: Built for easy integration into existing Spring Boot or standalone Java applications.

## Getting Started

### Prerequisites

* JDK 17 or higher
* Maven
* Paradex API Credentials

### Installation

Clone the repository and install it to your local Maven repository:

```bash
git clone https://github.com/Wayruha/paradex-java-lib.git
cd paradex-java-lib
mvn clean install
```