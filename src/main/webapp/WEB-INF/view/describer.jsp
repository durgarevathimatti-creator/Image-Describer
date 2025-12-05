<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Describer</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        textarea, pre { width: 100%; padding: 15px; margin-top: 20px; }
        input[type=file], input[type=text] { padding: 10px; margin: 10px 0; }
        button { padding: 12px 24px; font-size: 16px; background: #4285f4; color: white; border: none; cursor: pointer; }
        button:hover { background: #3367d6; }
        .result { background: #f1f3f4; border-radius: 8px; padding: 20px; margin-top: 20px; white-space: pre-wrap; }
    </style>
</head>
<body>
    <h1>Image Describer (Powered by Gemini)</h1>
    
    <h3>Upload Image</h3>
    <input type="file" id="fileInput" accept="image/*">
    <button onclick="describeFile()">Describe Image</button>
    
    <h3>Or enter Image URL</h3>
    <input type="text" id="urlInput" placeholder="https://example.com/image.jpg" style="width: 500px;">
    <button onclick="describeUrl()">Describe from URL</button>
    
    <div id="result" class="result" style="display:none;"></div>

<script>
async function describeFile() {
    const file = document.getElementById("fileInput").files[0];
    if (!file) { alert("Please select an image"); return; }
    
    const formData = new FormData();
    formData.append("file", file);
    
    const res = await fetch("/api/describe/file", { method: "POST", body: formData });
    const text = await res.text();
    document.getElementById("result").style.display = "block";
    document.getElementById("result").textContent = text;
}

async function describeUrl() {
    const url = document.getElementById("urlInput").value.trim();
    if (!url) { alert("Please enter URL"); return; }
    
    const res = await fetch("/api/describe/url", {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: url
    });
    const text = await res.text();
    document.getElementById("result").style.display = "block";
    document.getElementById("result").textContent = text;
}
</script>
</body>
</html>