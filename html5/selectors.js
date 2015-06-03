function load() {

  document.getElementById("render").innerHTML = document.getElementById("pre").innerText;

  document.getElementById("pre").onkeyup = function(e) {
      try {
          document.getElementById("render").innerHTML = document.getElementById("pre").innerText;
      }
      catch(err) {}
  };
  document.getElementById("input").onkeyup = function (e) {
      try {
          document.getElementById("styleSection").innerText = document.getElementById("input").innerText;
      }
      catch(err) {}
  };
  
  var input = document.getElementById("input");
  input.onclick = function (e) {
      input.classList.remove('watermark');
      input.innerText = "";
      input.onclick = null;
  };

}