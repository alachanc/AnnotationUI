
import './App.css';
import Modal from 'react-modal';
import { useState, useEffect, useRef } from 'react';
import {
  StompSessionProvider,
  useSubscription,
  useStompClient
} from "react-stomp-hooks";

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
}
//TODO: When adding new topic, xpath view is blank until switch back
const stompURL = 'http://localhost:8080/websocket-topic'
const buildOutMsg = (t, op, x) => { return { topicLabel: t, topicOp: op, topicXpath: x } }

function App() {
  return (
    <StompSessionProvider url={stompURL}>
      <Topics />
    </StompSessionProvider>
  );
}

function Topics() {
  //Topics := [{'topicLabel': '', 'topicXpath': ''}]
  const [topics, setTopics] = useState(null);
  const [topicModal, setTopicModal] = useState(false);
  const [xpathModal, setXpathModal] = useState(false);

  const toggleTModal = () => setTopicModal(x => !x)
  const toggleXModal = () => setXpathModal(x => !x)
  const topicsToOptions = () => topics ? topics.map(t => { return <option key={t.topicLabel} value={t.topicLabel}>{t.topicLabel}</option> }) : <></>
  const getDefaultT = () => topics ? topics[0].topicLabel : ''
  const getDefaultX = () => topics ? topics[0].topicXpath : ''

  const stompClient = useStompClient();

  useSubscription("/topic/topics", (m) => setTopics(JSON.parse(m.body).topics.map(t=>{ var [tl, tx] = t.split("::");
  return {topicLabel: tl, topicXpath: tx}})));

  const sendMessage = (m) => {
    if (stompClient) {
      stompClient.publish({
        destination: "/app/topics",
        body: JSON.stringify(m)
      })
    } else {
      console.log("Send Message did not work!!!")
    }
  }

  const handleSwitch = (e) => {
    e.preventDefault()
    e.stopPropagation()
    var t = topics.filter(to=>to.topicLabel === e.target.value).pop()
    sendMessage(buildOutMsg(t.topicLabel, 'SWITCH', t.topicXpath))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    e.stopPropagation()
    var [k, k_] = e.nativeEvent.submitter.name.split('_')
    var v = e.target[0].value
    if (k_ === "T") {
      sendMessage(buildOutMsg(v, k, ""))
      toggleTModal()
    } else {
      sendMessage(buildOutMsg("", k, v))
      toggleXModal()
    }
  }
  useEffect(()=>{ sendMessage(buildOutMsg('', '', '')) }, [stompClient])
  return (

    <div>
      <div>
        <div>Current Topic: {getDefaultT()}</div>
        <select id="TOPICS" onChange={handleSwitch}>
          {topicsToOptions()}
        </select>
        <button onClick={toggleTModal}>Modify Topic Model</button>
        <Modal isOpen={topicModal} onRequestClose={toggleTModal} style={customStyles} ariaHideApp={false}>
          <div>Modify Topic</div>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              defaultValue={getDefaultT()}/>
            <button type="submit" name="ADD_T">Add Sub-Topic</button>
            <button type="submit" name="EDIT_T">Edit</button>
            <button type="submit" name="DELETE_T">Delete</button>
          </form>
        </Modal>
        <div>
                  <input defaultValue={getDefaultX()} readOnly />
                  <button onClick={handleSubmit} type="submit" name="DELETE_X">Delete Last Xpath</button>
                  <button onClick={toggleXModal} >Edit Xpath</button>
                </div>
                <Modal isOpen={xpathModal} onRequestClose={toggleXModal} style={customStyles} ariaHideApp={false}>
                  <div>Modify XPath</div>

                  <form onSubmit={handleSubmit}>
                    <input
                      defaultValue={getDefaultX()}
                      type="text"/>
                    <button type="submit" name="EDIT_X">DONE</button>
                  </form>
                </Modal>
      </div>

    </div>
  )
}
export default App;
