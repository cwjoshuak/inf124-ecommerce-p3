let recentTable = document.getElementById("recent");

console.log(recentTable);
if (recentTable !== null) {

	let tableParent = recentTable.parentElement;
	tableParent.insertBefore(recentTable, tableParent.firstChild.nextSibling.nextSibling);

}
console.log("KARTU");

let cards = document.getElementsByClassName("card");
console.log(cards);

for (let i = 0; i < cards.length; i++) {
  cards[i].addEventListener("mouseenter", (event) => {
    event.currentTarget.style.boxShadow = "2px 2px 10px 3px rgba(0, 0, 0, 0.3)";
  });
  cards[i].addEventListener("mouseleave", (event) => {
    event.currentTarget.style.boxShadow =
      "1px 2px 3px 1px rgba(0, 0, 0, 0.2), 1px 4px 4px 1px rgba(0, 0, 0, 0.19)";
  });
}

let colors = document.getElementsByClassName("colors");
console.log(colors);
for (let i = 0; i < colors.length; i++) {
  let anchor = colors[i].parentElement.parentElement;

  let id = anchor.id.split("-")[1];
  let colorCircles = colors[i].childNodes;
  console.log("childnodes");
  console.log(colors[i].childNodes);

    for (let j = 0; j < colorCircles.length; j++) {
    if (j == 0) {
      console.log(colorCircles);
      let name = colorCircles[j].attributes["name"].value;
      console.log(name);
      anchor.setAttribute("href", "./product?id=" + id + "&color=" + name);
    }

    colorCircles[j].addEventListener("mouseenter", (event) => {
      i = document.getElementById("img-" + id);

      i.setAttribute("src", "./assets/" + id + "/product_" + j + ".jpg");
      var a = document.getElementById("a-" + id);
      a.setAttribute(
        "href",
        "./product?id=" +
          id +
          "&color=" +
          event.target.attributes.name.value
      );
    });
  }
}
