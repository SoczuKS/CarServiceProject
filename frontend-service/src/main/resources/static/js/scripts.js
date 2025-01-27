function setRequired(container, isRequired) {
    const elements = container.getElementsByTagName('input');
    for (let i = 0; i < elements.length; i++) {
        elements[i].required = isRequired;
    }
}

function toggleClientData() {
    const individualClientData = document.getElementById('individual_client_data');
    if (individualClientData === null) {
        return;
    }
    const companyClientData = document.getElementById('company_client_data');
    if (companyClientData === null) {
        return;
    }
    const individualCheckbox = document.getElementById('individual');
    if (individualCheckbox === null) {
        return;
    }
    const isIndividual = individualCheckbox.checked;

    if (isIndividual) {
        individualClientData.style.display = 'block';
        companyClientData.style.display = 'none';
        setRequired(individualClientData, true);
        setRequired(companyClientData, false);
    } else {
        individualClientData.style.display = 'none';
        companyClientData.style.display = 'block';
        setRequired(individualClientData, false);
        setRequired(companyClientData, true);
    }
}

function setupNumberInputEvents() {
    document.getElementById("price").addEventListener("change", function (event) {
        roundInputNumber(event, 2);
    });
    document.getElementById("quantity").addEventListener("change", function (event) {
        roundInputNumber(event, 0);
    });
}

function roundInputNumber(event, precision) {
    const input = event.target;
    const value = parseFloat(input.value);
    if (!isNaN(value)) {
        input.value = value.toFixed(precision);
    }
}
