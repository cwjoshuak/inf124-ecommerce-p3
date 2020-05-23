let orderForm = document.getElementById("odForm");
let oih3 = document.createElement("h3");
oih3.textContent = "Order Information";

document.title =
    "eCrocs | " +
    document.getElementsByClassName("product-right")[0].children[0].textContent;
orderForm.appendChild(oih3);
let form = document.createElement("form");
form.action = "javascript:;";
form.onsubmit = (ev) => insertToDB(ev);

form.innerHTML = `<ul>
  <li>
    <label for="qty">Quantity</label>
    <input type="number" id="qty" name="qty" value="1" min="1" required/>
  </li>
  <br /> <br />
    <button class= "cart" type="submit">Add To Cart</button>
    
</ul>`;
orderForm.appendChild(form);

function parseQuery(queryString) {
    var query = {};
    var pairs = (queryString[0] === "?"
            ? queryString.substr(1)
            : queryString
    ).split("&");
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split("=");
        query[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1] || "");
    }
    return query;
}
let queryParams = parseQuery(window.location.search);

function insertToDB(ev) {
    let form = Object.entries(ev.target);
    console.log(form);
    console.log(
        JSON.stringify({
            shoe_id: queryParams["id"],
            color: queryParams["color"],
            shoe_size: document.getElementById("size-selector").value,
            quantity: form[0][1].value,
            base_price: document.getElementById("baseprice").textContent.substring(1)
        })
    );
}
