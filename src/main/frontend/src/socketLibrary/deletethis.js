////Example URL
//const changeMe = "example.xml"
////Replaces the empty text-string of an element whose 'id'==v w/ some string, r
//const loadValue = (v, r) => $(`#${v}`).text($(`#${v}`).text().replace("", r))
////Takes an object whose keys are the units to access and data is a list of values required for that unit
////"data" can be all values for a doc or specific (example: edit values)
//const populateValues = (unitData) => Object.entries(unitData)
//    .forEach(([unit, data]) => $(document).$((() => {
//        $.ajax({
//            type: "GET",
//            url: changeMe,
//            dataType: "xml",
//            success: function (x) {
//                unit.forEach(u => $(x).find(u).each(() => data.forEach(d => loadValue(d, $(this).find(d).text()))))
//            }
//        })
//    })))
//export default { populateValues }