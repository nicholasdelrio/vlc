function image_URLs(result)
{
	//alert("in method");
	$('#loading').remove();
	puttext();
	for(var i=0;i<result.results.length;i++)
	{
		display(result.results[i].image_url, 100,100,result.results[i].source_url);
		if((i+1)%3==0)
		{
			document.write("<br>");
			document.write("<br>");
		}
	}
	document.write("<br>");
	document.write("<br>");
	document.write("<br>");
	document.write("<button>Back To Main Page</button>");
	$("button").click(function()
	{
		document.location.href='MyHtml.html';
	});
}
function display(img_url, width, height,src_url)
{
	//document.write("<p></p>");
	var img = document.createElement("img");
	img.src=img_url;
	img.width = width;
	img.height = height;
	img.addEventListener('click', function() 
	{
		window.location.href=src_url;
	}, false);
	img.onmouseover=function()
	{
		bigImg(this);
		document.write("<p>"+src_url+"</p>");
	};
	img.onmouseout=function()
	{
		normalImg(this);
		$("p").text("");
	};
	
	document.write("<ahref='#' rel='tooltip' title='Click to go to: "+src_url+"' id='tooltip'>");
	document.write("<a href="+src_url+" target='_blank'><img src="+img_url+" width="+width+" height="+height+" hspace=20 onmouseover='bigImg(this)' onmouseout='normalImg(this)' onmouseclick='clickAction("+src_url+")'/></a>");
	//document.body.appendChild(img);
}
function bigImg(x)
{
x.style.height="125px";
x.style.width="125px";
$('#toolstip').tooltip({'placement':'top', 'trigger' : 'hover'});
}

function normalImg(x)
{
x.style.height="100px";
x.style.width="100px";
}
function puttext()
{
	document.writeln("<font color='#00000'><h2>Clicking on an image will take you to the source of the image.</h2></font>");
}