# Backend Challenge
Given the data about Food Trucks in San Francisco: https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat
You will build an API that allows the following operations on this data set:
- Search by name of applicant
- Search by expiration date, to find whose permits have expired
- Search by street name
- Add new food truck entry to the dataset
- Given a delivery location, find out the closest truck possible.

### Warning: The API and Database is hosted on render (https://render.com/) since that is the only free tier I had, and the API calls will have a cold start if unused so please wait for a while after the first call timesout and then evaluate.<br/>

# APIs
## /api/search
Use this API to search using applicant name, street name and expiration. At least one of those is mandatory and if multiple are provided logical AND condition is applied.<br />
**URL:** https://credit-saison-breejesh.onrender.com/api/search <br/>
**Verb:** POST<br />
**Sample Request Body:**
```json
{
    "applicantName": "Cochinita",
    "streetName": "1219 07TH AVE",
    "expirationDate": "2022-12-10T18:25:43.511Z",
    "pageNumber": 1
}
```
## /api/find-closest
Use this API to find closest food trucks to a given point (latitude and longitude). You can specify the number of result entries needed, automatically limited to minimum 1 and maximum 10.<br />
**URL:** https://credit-saison-breejesh.onrender.com/api/find-closest </br>
**Verb:** GET<br />
**Sample URL:** https://credit-saison-breejesh.onrender.com/api/find-closest?longitude=-122.4646&latitude=37.765495&limit=5<br/>

## /api/add
Use this API to add a food truck permit entry. Limited columns were taken since this is just a demo.<br/>
**URL:** https://credit-saison-breejesh.onrender.com/api/add <br/>
**Verb:** POST<br/>
**Sample Request Body:**
```json
{
    "applicantName": "Breejesh",
    "streetName": "Jane Street",
    "expirationDate": "2025-12-10T18:25:43.511Z",
    "approvalDate": "2023-11-10T18:25:43.511Z",
    "latitude": 41.766759,
    "longitude":  -72.701173
}
```

