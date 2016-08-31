import {AsyncStorage} from "react-native";

var storageService ={
   async saveData_async(key,value,errorCalllback,successCallback){
      try{
      await AsyncStorage.setItem(key,value,(error)=>{if(error) {errorCalllback(error);}else{successCallback("save data success");}});
     }catch(error){
       console.log("langneng setItem error:"+error);
     }
   },

   async getData_async(key,errorCalllback,resultCallback){
     try{
      await AsyncStorage.getItem(key,(error,result)=>{if(error) {errorCalllback(error)}else{ resultCallback(result)}});
    }catch(error){
       console.log("langneng getItem error:"+error);
    }
  },

  async deleteData_async(key,errorCalllback,successCallback){
    try{
     await AsyncStorage.removeItem(key,(error)=>{if(error) {errorCalllback(error)}else{ successCallback("delete data success")}});
   }catch(error){
      console.log("langneng removeItem error:"+error);
   }
 },

   saveData_sync(key,value){
       var setItemFinish =false;
       try{
       AsyncStorage.setItem(key,value,(error,result)=>{setItemFinish = true;console.log("langneng result:"+result+" error:"+error+" setItemFinish:"+setItemFinish);});
       // setTimeout(()=>{console.log("langneng after setItem 3000 ms");},3000);
        console.log("langneng after setItem");
        console.log("langneng before for setItemFinish:"+setItemFinish);
        for(i=0;i<100000;i++){
            console.log("langneng i:"+i);
            // console.log("langneng setItemFinish:"+setItemFinish);
            if(setItemFinish==true) {
              // console.log("langneng setItemFinish:"+setItemFinish);
              break;
            }
        }
        // setInterval(()=>{console.log("langneng setItemFinish:"+setItemFinish);if(setItemFinish) console.log("langneng setItemFinish:"+setItemFinish);},2000);
       //  return;
      }catch(error){
        console.log("langneng saveValue error:"+error);
      }
    },
  //  password(complete){
  //       AsyncStorage.getItem('password', (error, object) => { if (error) { console.log('error:' + error.message); complete(); } else { complete(object); } }) }
};

module.exports = storageService;
