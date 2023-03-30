  var i = document.getElementById("maxPersonSize").getAttribute("name");
     var j = document.getElementById("maxPersonSize").getAttribute("name");
     var index = 0;

     function addNewContactPhone() {
     let newJ = j-1;
      let newI = i-2;
      	index++;
      const h2e = document.getElementById(`newAdderPhoneId-${newJ}`);
      let adderHtmlPhone = '<div class="col-md-12" id="contactPhoneAdder"> <div class="form-group bmd-form-group mt-0"> <label class="label">Phone</label> <input type="tel" class="form-control" id="personPhone" name="personPhone['+newI+']" value="" required><input name="personData:personContact" type="text" hidden="hidden" value="'+newI+':'+index+'" ></div></div><p id="newAdderPhoneId-'+j+'"></p>';
       j++;
      h2e.insertAdjacentHTML("afterend", adderHtmlPhone);
    }
    function addNewContactInfo() {
        let newI = i-1;
        let newJ = j-1;
            index=0;
           const h2 = document.getElementById(`newAdderId-${newI}`);
           let adderHtml = '<div id="contactInfoAdder" class="col-md-12 d-flex justify-content-center"> <div class="row d-flex justify-content-center" id="deletedPerson-'+newI+'"> <div class="col-md-3">  <div class="form-group bmd-form-group mt-0"> <label class="label">Person Name </label><input type="text" class="form-control" id="personName" name="personName" value="" required></div></div><div class="col-md-3"><div class="form-group bmd-form-group mt-0"><label class="label">Email</label><input type="email" class="form-control" id="personEmail" name="personEmail" value=""></div></div> <div class="col-md-3 d-flex justify-content-center flex-column"> <div class="col-md-12" id="contactPhoneAdder"> <div class="form-group bmd-form-group mt-0"> <label class="label">Phone</label> <input type="tel" class="form-control" id="personPhone" name="personPhone['+newI+']" value="" required><input name="personCorrectDataIter" type="text" hidden="hidden" value="'+newI+':0" ></div></div> <p id="newAdderPhoneId-'+j+'"></p></div><div class="col-md-1 d-flex align-item-center justify-content-center"><div class="form-group bmd-form-group  d-flex align-items-center"> <div id="contactInfoDelete-button" class="col-md-12"> <span class="btn btn-warning" onclick="deleteContactInfo(this)" name="'+newI+'"> <i class="material-icons">delete</i></span></div></div></div></div></div> <p id="newAdderId-'+i+'" ></p>';
           i++;
           j++;
           h2.insertAdjacentHTML("afterend", adderHtml);
    }

     function deleteContactInfo(element) {
         let el= element.getAttribute("name")
          const h2d = document.getElementById(`deletedPerson-${el}`);
          h2d.remove();
          i--;
          j--;
     }
