function onLoad() {
    document.addEventListener('DOMContentLoaded', function() {
        let tabLinks = document.querySelectorAll('.tab_links');
        tabLinks.forEach(function(link) {
            link.addEventListener('click', function(event) {
                openTab(event, link.getAttribute('data-tab'));
            });
        });
    });
}

function openTab(event, tabName) {
    let tabContent = document.getElementsByClassName("tab_content");
    for (let i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }

    let tabLinks = document.getElementsByClassName("tab_links");
    for (let i = 0; i < tabLinks.length; i++) {
        tabLinks[i].className = tabLinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    event.currentTarget.className += " active";
}

onLoad();