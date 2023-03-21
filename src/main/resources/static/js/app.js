function showFruitList() {
    let input = $('#textInput').val();
    if (input == '') {
      $('#results').html('');
      return;
    }
    let url = '/fruit?search=' + input;
    $.get(url, function (data) {
        let fruits = data;
        let listItems = '';
        fruits.forEach((fruit, index) => {
            listItems += '<a href="https://simple.wikipedia.org/wiki/' + fruit + '" target="_blank">';
            listItems += '<li';
            if (index % 2 == 1) {
                listItems += ' class=odd';
            }
            listItems += '>' + fruit + '</li>';
            listItems += '</a>';
        });
        $('#results').html('<ul style="border-bottom: 1px solid black;">' + listItems + '</ul>');
    });
}

function showDictionaryList() {
    let input = $('#textInput').val();
    let url = '/dictionary?search=' + input;
    $.get(url, function (data) {
        let words = data.slice(0, 50); // In case someone's max words doesn't work, limit them here to not crash the page.
        let listItems = '';
        words.forEach((word, index) => {
            listItems += '<a href="https://www.merriam-webster.com/dictionary/' + word + '" target="_blank">';
            listItems += '<li';
            if (index % 2 == 1) {
                listItems += ' class=odd';
            }
            listItems += '>' + word + '</li>';
            listItems += '</a>';
        });
        $('#results').html('<ul style="border-bottom: 1px solid black;">' + listItems + '</ul>');
    });
}
