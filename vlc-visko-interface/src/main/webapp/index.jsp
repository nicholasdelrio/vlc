<html>
<body>
<h2>HTML Batch URL VisKo Processing</h2>

<h4>Sample Input</h4>
<pre>
{
  "source_urls":
  [
    {"source_url" : "http://www.google.com"},
    {"source_url" : "http://www.nsf.gov"},
    {"source_url" : "http://www.youtube.com"},
    {"source_url" : "http://iw.cs.utep.edu:8080/visko-web"},
    {"source_url" : "http://cybershare.utep.edu/"}
  ]
}
</pre>

<h4>Sample Call</h4>
<a href="/vlc-visko-interface/HTMLQueryBatchService?jsonSources=%7B%0A%20%20%22source_urls%22%3A%0A%20%20%5B%0A%20%20%20%20%7B%22source_url%22%20%3A%20%22http%3A%2F%2Fwww.google.com%22%7D%2C%0A%20%20%20%20%7B%22source_url%22%20%3A%20%22http%3A%2F%2Fwww.nsf.gov%22%7D%2C%0A%20%20%20%20%7B%22source_url%22%20%3A%20%22http%3A%2F%2Fwww.youtube.com%22%7D%2C%0A%20%20%20%20%7B%22source_url%22%20%3A%20%22http%3A%2F%2Fiw.cs.utep.edu%3A8080%2Fvisko-web%22%7D%2C%0A%20%20%20%20%7B%22source_url%22%20%3A%20%22http%3A%2F%2Fcybershare.utep.edu%2F%22%7D%0A%20%20%5D%0A%7D">Click To Execute</a>

<h4>Invocation Details</h4>
<ul>
	<li>Base URL: http://iw.cs.utep.edu:8080/vlc-visko-interface/HTMLQueryBatchService
	<li>Parameter: jsonSources - specifies a UTF-8 encoded JSON list of web page URLs to be transformed into images
</ul>

</body>
</html>
