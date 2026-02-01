## AI Agent Java Demo (Local LLaMA)

This repository contains a **standalone Java example** demonstrating direct integration with a **local LLaMA-based LLM**.


The purpose of this example is to demonstrate the **core integration boundary** between a Java application and a Large Language Model (LLM):
- programmatic prompt construction
- plain HTTP communication
- explicit request/response handling
- no frameworks or SDK abstractions

This project intentionally focuses on **clarity and mechanics** rather than features, scalability, or production concerns.

---

## Repository Layout

```
ai-agent-java-demo/
  build.gradle
  settings.gradle

  src/
    main/
      java/
        com/
          celso/
            agentsjavademo/
              ProgrammaticPrompting.java
              LLM.java
              Message.java
     test/
      java/
        com/
          celso/
            agentsjavademo/
              MessageTest.java
              PromptBuilderTest.java

  README.md
  LICENSE
  .gitattributes
```

---

## Getting Started


### Requirements
- Java 17+
- A local LLaMA server running on `http://localhost:8080` (TinyLlama model is ideal)


### Run

This project requires a **locally running LLaMA-compatible server** exposing an OpenAI-compatible API at:

http://localhost:8080


Step 1 — Start the local LLM server
Start a local LLaMA server (for example, using llama.cpp / llama-server) with a TinyLLaMA model.

Example command:

./llama-server \
  --model /path/to/tinyllama.gguf \
  --port 8080 \
  --ctx-size 2048 \
  --threads 4

The exact installation path and model location are environment-specific.
	

Step 2 — Run the Java application
Once the LLM server is running, execute:

```bash
./gradlew run
```
and wait a few seconds or minutes (depending on your machine and prompt/system prompt).


Its System Prompt: "You are a cook ready to help with everyday cooking questions."
The LLaMA model will answer the prompt:
"What are the ingredients of a cake?"

Note: TinyLlama can make mistakes. Check out this important information.


---

### Run tests


The tests do **not** require a running LLM. HTTP-level tests can be added later using an in-memory mock server.


``` bash
./gradlew test
```

---


### How this works

- The Java application builds the prompt programmatically
- The prompt is sent to a locally running LLaMA server via HTTP
- The response is received and processed by the Java runtime
- There is no browser, notebook, or interactive UI involved

This is an example of **LLM integration at the application level**, similar to how LLMs are used in production systems.


### Notes

This project is inspired by concepts from the **"AI Agents in Java"** course by Jules White.

The original examples rely on OpenAI API keys.
In this repository, the code was reorganized and adapted to work with a **locally hosted LLaMA-compatible LLM (TinyLLaMA)**.

---

## Author

This project was developed by an engineer and data scientist with a background in:

* Postgraduate degree in **Data Science and Analytics (USP)**
* Bachelor's degree in **Computer Engineering (UERJ)**
* Special interest in statistical models, interpretability, and applied AI

---


## Contact  

- [LinkedIn](https://linkedin.com/in/celso-m-silva)  
- Or open an [issue](https://github.com/celsomsilva/ai-agent-java-demo/issues)
